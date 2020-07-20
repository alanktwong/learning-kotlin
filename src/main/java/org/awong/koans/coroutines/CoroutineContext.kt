package org.awong.koans.coroutines

import kotlinx.coroutines.*

class CoroutineContext {

    fun launchCoroutinesWithContext() = runBlocking<Unit> {
        launch { // context of the parent, main runBlocking coroutine
            println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
            println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
            println("Default               : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
        }
    }

    fun confinedVersusUnconfinedDispatchers() = runBlocking<Unit> {
        launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
            println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
            delay(500)
            println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
        }
        launch { // context of the parent, main runBlocking coroutine
            println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
            delay(1000)
            println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
        }
    }

    fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

    fun loggingCoroutineContext() = runBlocking {
        // Requires -Dkotlinx.coroutines.debug
        val a = async {
            log("I'm computing a piece of the answer")
            6
        }
        val b = async {
            log("I'm computing another piece of the answer")
            7
        }
        log("The answer is ${a.await() * b.await()}")
    }

    fun jumpBetweenThreads() {
        newSingleThreadContext("Ctx1").use { ctx1 ->
            newSingleThreadContext("Ctx2").use { ctx2 ->
                runBlocking(ctx1) {
                    log("Started in ctx1")
                    log("My job is ${coroutineContext[Job]}")
                    withContext(ctx2) {
                        log("Working in ctx2")
                        log("My job is ${coroutineContext[Job]}")
                    }
                    log("Back to ctx1")
                    log("My job is ${coroutineContext[Job]}")
                }
            }
        }
    }

    fun childrenOfCoroutine() = runBlocking {
        log("START launch a coroutine to process some kind of incoming request")
        val request = launch {
            // it spawns two other jobs, one with GlobalScope
            GlobalScope.launch {
                log("${coroutineContext[Job]}: I run in GlobalScope and execute independently!")
                delay(1000)
                log("${coroutineContext[Job]}: I am not affected by cancellation of the request")
            }
            // and the other inherits the parent context
            launch {
                delay(100)
                log("${coroutineContext[Job]}: I am a child of the request coroutine")
                delay(1000)
                log("${coroutineContext[Job]}: I will not execute this line if my parent request is cancelled")
            }
        }
        delay(500)
        log("cancel processing of the request")
        request.cancel()
        delay(1000) // delay a second to see what happens
        log("main: Who has survived request cancellation?")
    }

    fun parentCoroutine() = runBlocking {
        log("START launch a coroutine to process some kind of incoming request")
        val request = launch {
            repeat(3) { i -> // launch a few children jobs
                launch  {
                    delay((i + 1) * 200L) // variable delay 200ms, 400ms, 600ms
                    log("Coroutine $i is done")
                }
            }
            log("request: I'm done and I don't explicitly join my children that are still active")
        }
        request.join() // wait for completion of the request, including all its children
        log("Now processing of the request is complete")
    }

    fun namedCoroutines() = runBlocking(CoroutineName("main")) {
        log("Started main coroutine")
        // run two background value computations
        val v1 = async(CoroutineName("v1coroutine")) {
            delay(500)
            log("Computing v1")
            252
        }
        val v2 = async(CoroutineName("v2coroutine")) {
            delay(1000)
            log("Computing v2")
            6
        }
        log("The answer for v1 / v2 = ${v1.await() / v2.await()}")
    }

    fun combiningContextElements() = runBlocking<Unit> {
        launch(Dispatchers.Default + CoroutineName("test")) {
            println("I'm working in thread ${Thread.currentThread().name}")
        }
    }

    fun runActivity() = runBlocking {
        val activity = Activity()
        log("run test function")
        activity.doSomething()
        log("Launched coroutines")
        delay(500L) // delay for half a second
        log("Destroying activity! Should cancel all coroutines")
        activity.destroy()
        delay(1000) // visually confirm that they don't work
    }

    val threadLocal = ThreadLocal<String?>() // declare thread-local variable

    fun useThreadLocalData() = runBlocking<Unit> {
        threadLocal.set("main")
        log("Pre-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
            log("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
            yield()
            log("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        }
        job.join()
        log("Post-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    }
}

class Activity {
    private val mainScope = CoroutineScope(Dispatchers.Default) // use Default for test purposes

    fun destroy() {
        mainScope.cancel()
    }

    fun doSomething() {
        // launch ten coroutines for a demo, each working for a different time
        repeat(10) { i ->
            mainScope.launch {
                delay((i + 1) * 200L) // variable delay 200ms, 400ms, ... etc
                println("Coroutine $i is done")
            }
        }
    }
}
