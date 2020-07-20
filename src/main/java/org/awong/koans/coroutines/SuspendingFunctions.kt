package org.awong.koans.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


class SuspendingFunctions {

    private suspend fun doSomethingUsefulOne(): Int {
        println("pretend we are doing something useful here")
        delay(1000L)
        return 13
    }

    private suspend fun doSomethingUsefulTwo(): Int {
        println("pretend we are doing something useful here, too")
        delay(1000L)
        return 29
    }

    // The result type of somethingUsefulOneAsync is Deferred<Int>
    private fun somethingUsefulOneAsync() = GlobalScope.async {
        doSomethingUsefulOne()
    }

    // The result type of somethingUsefulTwoAsync is Deferred<Int>
    private fun somethingUsefulTwoAsync() = GlobalScope.async {
        doSomethingUsefulTwo()
    }
    fun composeTwoFunctionsSequentially() = runBlocking {
        println("START compose 2 functions sequentially")
        val time = measureTimeMillis {
            val one = doSomethingUsefulOne()
            val two = doSomethingUsefulTwo()
            println("The answer is ${one + two}")
        }
        println("Completed in $time ms")
    }

    fun composeTwoFunctionsAsync() = runBlocking {
        println("START compose 2 functions with async")
        val time = measureTimeMillis {
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }

    fun composeTwoFunctionsAsyncLazily() = runBlocking {
        println("START compose 2 functions with async and start them lazily")

        val time = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY) {
                doSomethingUsefulOne()
            }
            val two = async(start = CoroutineStart.LAZY) {
                doSomethingUsefulTwo()
            }
            println("Pretend to do some computation")

            // start the first one
            one.start()
            // start the second one
            two.start()
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }

    // note that we don't have `runBlocking` to the right of `main` in this example
    // N.B. this-style is bad Kotlin practice
    fun composeAsyncStyleFunctions() {
        println("START compose 2 async-style functions")
        val time = measureTimeMillis {
            // we can initiate async actions outside of a coroutine
            val one = somethingUsefulOneAsync()
            val two = somethingUsefulTwoAsync()
            // but waiting for a result must involve either suspending or blocking.
            // here we use `runBlocking { ... }` to block the main thread while waiting for the result
            runBlocking {
                println("The answer is ${one.await() + two.await()}")
            }
        }
        println("Completed in $time ms")
    }

    private suspend fun concurrentSum(): Int = coroutineScope {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        one.await() + two.await()
    }

    fun structuredConcurrency() = runBlocking {
        println("START compose 2 coroutines using structured concurrency")

        val time = measureTimeMillis {
            println("The answer is ${concurrentSum()}")
        }
        println("Completed in $time ms")
    }


    fun structuredConcurrencyWithErrorHandling() = runBlocking<Unit> {
        println("START compose 2 coroutines using structured concurrency and handle errors")
        try {
            failedConcurrentSum()
        } catch(e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }

    private suspend fun failedConcurrentSum(): Int = coroutineScope {
        val one = async {
            try {
                delay(Long.MAX_VALUE) // Emulates very long computation
                42
            } finally {
                println("1st child was cancelled")
            }
        }
        val two = async<Int> {
            println("2nd child throws an exception")
            throw ArithmeticException()
        }
        one.await() + two.await()
    }

}