package org.awong.koans.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis


class AsyncFlow {

    private fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

    private fun simpleSequence(): Sequence<Int> = sequence { // sequence builder
        for (i in 1..3) {
            Thread.sleep(100) // pretend we are computing it
            yield(i) // yield next value
        }
    }

    fun computeSequentially() {
        log("START: compute sequentially")
        simpleSequence().forEach { value -> println(value) }
    }

    private suspend fun simpleUnblock(): List<Int> {
        delay(1000) // pretend we are doing something asynchronous here
        return listOf(1, 2, 3)
    }

    fun suspendComputeSequentially() = runBlocking {
        log("START: compute sequentially with suspend")
        simpleUnblock().forEach { value -> log(value.toString()) }
    }

    private fun simpleFlow(): Flow<Int> = flow { // flow builder
        for (i in 1..3) {
            delay(100) // pretend we are doing something useful here
            println("Emitting $i")
            emit(i) // emit next value
        }
    }

    fun launchSimpleFlow() = runBlocking {
        log("START: Launch a concurrent coroutine to check if the main thread is blocked")
        launch {
            for (k in 1..3) {
                log("I'm not blocked $k")
                delay(100)
            }
        }
        // Collect the flow
        simpleFlow().collect { value -> log(value.toString()) }
    }

    private fun coldFlow(): Flow<Int> = flow {
        log("Flow started")
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }

    fun launchColdFlow() = runBlocking {
        log("START: Launch a flow which is a cold stream")

        println("Calling simple function...")
        val flow = coldFlow()
        println("Calling collect...")
        flow.collect { value -> log(value.toString()) }
        println("Calling collect again...")
        flow.collect { value -> log(value.toString()) }
    }

    fun launchFlowWithTimeout() = runBlocking {
        log("START: Launch a flow which will timeout after 250ms")
        withTimeoutOrNull(250) {
            simpleFlow().collect { value -> println(value) }
        }
        println("Done")
    }

    fun rangeAsFlow() = runBlocking {
        log("START: Convert an integer range to a flow")
        (1..3).asFlow().collect { value -> println(value) }
    }


    private suspend fun performRequest(request: Int): String {
        log("imitate long-running asynchronous work")
        delay(1000)
        return "response $request"
    }

    fun mapFlow() = runBlocking {
        (1..3).asFlow() // a flow of requests
                .map { request -> performRequest(request) }
                .collect { response -> log(response) }
    }

    fun transformFlow() = runBlocking {
        (1..3).asFlow() // a flow of requests
                .transform { request ->
                    emit("Making request $request")
                    emit(performRequest(request))
                }
                .collect { response -> log(response) }
    }

    private fun numbers(): Flow<Int> = flow {
        try {
            emit(1)
            emit(2)
            log("This line will not execute")
            emit(3)
        } finally {
            log("Finally in numbers")
        }
    }

    fun takeFew() = runBlocking {
        numbers().take(2) // take only the first two
                .collect { value -> println(value) }
    }

    fun mapReduce() = runBlocking {
        val sum = (1..5).asFlow()
                .map { it * it } // squares of numbers from 1 to 5
                .reduce { a, b -> a + b } // sum them (terminal operator)
        log(sum.toString())
    }

    fun sequentialFlow() = runBlocking {
        (1..5).asFlow()
                .filter {
                    log("Filter $it")
                    it % 2 == 0
                }
                .map {
                    log("Map $it")
                    "string $it"
                }.collect {
                    log("Collect $it")
                }
    }

    private fun simpleContext(): Flow<Int> = flow {
        log("Started simple flow")
        for (i in 1..3) {
            emit(i)
        }
    }

    fun preserveContext() = runBlocking {
        simpleContext().collect { value -> log("Collected $value") }
    }

    fun wrongEmission(): Flow<Int> = flow {
        log("The WRONG way to change context for CPU-consuming code in flow builder")
        withContext(Dispatchers.Default) {
            for (i in 1..3) {
                Thread.sleep(100) // pretend we are computing it in CPU-consuming way
                emit(i) // emit next value
            }
        }
    }

    private fun simpleFlowOn(): Flow<Int> = flow {
        for (i in 1..3) {
            Thread.sleep(100) // pretend we are computing it in CPU-consuming way
            log("Emitting $i")
            emit(i) // emit next value
        }
    }.flowOn(Dispatchers.Default) // RIGHT way to change context for CPU-consuming code in flow builder

    fun launchFlowOn() = runBlocking {
        log("START: launch flow on")
        simpleFlowOn().collect { value ->
            log("Collected $value")
        }
    }

    private fun buffered(): Flow<Int> = flow {
        for (i in 1..3) {
            delay(100) // pretend we are asynchronously waiting 100 ms
            emit(i) // emit next value
        }
    }

    fun unbuffered() = runBlocking {
        log("START: launch flow without buffering")
        val time = measureTimeMillis {
            buffered().collect { value ->
                delay(300) // pretend we are processing it for 300 ms
                log(value.toString())
            }
        }
        log("Collected in $time ms")
    }

    fun buffering() = runBlocking {
        log("START: launch flow with buffering")
        val time = measureTimeMillis {
            // buffer emissions, don't wait
            buffered().buffer()
                    .collect { value ->
                        delay(300) // pretend we are processing it for 300 ms
                        log(value.toString())
                    }
        }
        log("Collected in $time ms")
    }

    fun conflateEmissions() = runBlocking {
        log("START: launch flow and conflate emissions")
        val time = measureTimeMillis {
            buffered()
                    .conflate() // conflate emissions, don't process each one
                    .collect { value ->
                        delay(300) // pretend we are processing it for 300 ms
                        log(value.toString())
                    }
        }
        println("Collected in $time ms")
    }

    fun collectLatest() = runBlocking {
        log("START: launch flow and collect latest emissions")
        val time = measureTimeMillis {
            buffered()
                    .collectLatest { value -> // cancel & restart on the latest value
                        log("Collecting $value")
                        delay(300) // pretend we are processing it for 300 ms
                        log("Done $value")
                    }
        }
        log("Collected in $time ms")
    }

    fun zipFlows() = runBlocking {
        log("START: zip flows")
        val nums = (1..3).asFlow() // numbers 1..3
        val strs = flowOf("one", "two", "three") // strings
        nums.zip(strs) { a, b -> "$a -> $b" } // compose a single string
                .collect { log(it) } // collect and print
    }

    fun zipOnEach() = runBlocking {
        log("START: zip flows on each")

        val nums = (1..3).asFlow().onEach { delay(300) } // numbers 1..3 every 300 ms
        val strs = flowOf("one", "two", "three").onEach { delay(400) } // strings every 400 ms
        val startTime = System.currentTimeMillis() // remember the start time
        nums.zip(strs) { a, b -> "$a -> $b" } // compose a single string with "zip"
                .collect { value -> // collect and print
                    println("$value at ${System.currentTimeMillis() - startTime} ms from start")
                }
    }

    fun combineFlows() = runBlocking {
        log("START: combine flows")

        val nums = (1..3).asFlow().onEach { delay(300) } // numbers 1..3 every 300 ms
        val strs = flowOf("one", "two", "three").onEach { delay(400) } // strings every 400 ms
        val startTime = System.currentTimeMillis() // remember the start time
        nums.combine(strs) { a, b -> "$a -> $b" } // compose a single string with "combine"
                .collect { value -> // collect and print
                    println("$value at ${System.currentTimeMillis() - startTime} ms from start")
                }
    }

    fun requestFlow(i: Int): Flow<String> = flow {
        emit("$i: 1st")
        delay(500) // wait 500 ms
        emit("$i: 2nd")
    }

    fun flatMapConcat() = runBlocking {
        log("START: flat map concat")
        val startTime = System.currentTimeMillis() // remember the start time
        (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
                .flatMapConcat { requestFlow(it) }
                .collect { value -> // collect and print
                    log("$value at ${System.currentTimeMillis() - startTime} ms from start")
                }
    }

    fun flatMapMerge() = runBlocking {
        log("START: flat map merge")
        val startTime = System.currentTimeMillis() // remember the start time
        (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
                .flatMapMerge { requestFlow(it) }
                .collect { value -> // collect and print
                    log("$value at ${System.currentTimeMillis() - startTime} ms from start")
                }
    }

    fun flatMapLatest() = runBlocking {
        log("START: flat map latest")
        val startTime = System.currentTimeMillis() // remember the start time
        (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
                .flatMapLatest { requestFlow(it) }
                .collect { value -> // collect and print
                    log("$value at ${System.currentTimeMillis() - startTime} ms from start")
                }
    }

    private fun emitAll(): Flow<Int> = flow {
        for (i in 1..3) {
            log("Emitting $i")
            emit(i) // emit next value
        }
    }

    fun collectTryCatch() = runBlocking {
        log("START: collector uses try/cattch")
        try {
            emitAll().collect { value ->
                log(value.toString())
                check(value <= 1) { "Collected $value" }
            }
        } catch (e: Throwable) {
            log("Caught $e")
        }
    }

    private fun tryEmitAll(): Flow<String> = flow {
        for (i in 1..3) {
            log("Emitting $i")
            emit(i) // emit next value
        }
    }
    .map { value ->
        check(value <= 1) { "Crashed on $value" }
        "string $value"
    }

    fun catchEverything() = runBlocking {
        log("START: collector catches everything")
        try {
            tryEmitAll().collect { value -> log(value) }
        } catch (e: Throwable) {
            log("Caught $e")
        }
    }

    fun catchAndThenEmit() = runBlocking {
        log("START: emit the text on catching an exception")
        tryEmitAll()
                .catch { e -> emit("Caught $e") } // emit on exception
                .collect { value -> log(value) }
    }

    fun catchTransparently() = runBlocking {
        log("START: catch transparently")
        emitAll()
                .catch { e -> log("Caught $e") } // does not catch downstream exceptions
                .collect { value ->
                    check(value <= 1) { "Collected $value" }
                    log(value.toString())
                }
    }

    fun catchDeclaratively() = runBlocking {
        log("START: catch declaratively")
        emitAll()
                .onEach { value ->
                    check(value <= 1) { "Collected $value" }
                    log(value.toString())
                }
                .catch { e -> log("Caught $e") }
                .collect()
    }
}