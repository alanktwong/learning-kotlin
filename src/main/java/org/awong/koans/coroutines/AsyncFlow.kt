package org.awong.koans.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


class AsyncFlow {

    private fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

    private fun simple(): Sequence<Int> = sequence { // sequence builder
        for (i in 1..3) {
            Thread.sleep(100) // pretend we are computing it
            yield(i) // yield next value
        }
    }

    fun computeSequentially() {
        log("START: compute sequentially")
        simple().forEach { value -> println(value) }
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


}