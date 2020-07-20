package org.awong.koans.coroutines

import kotlinx.coroutines.*

class CoroutineBasics {
    fun firstCoroutine() = runBlocking {
        println("START: start main coroutine")
        GlobalScope.launch {
            println("launch a new coroutine in background and continue")
            delay(1000L)
            println("World!")
        }
        println("Hello,")       // main coroutine continues here immediately
        delay(2000L)  // delaying for 2 seconds to keep JVM alive
    }

    fun joinCoroutine() = runBlocking  {
        println("START: launch a new coroutine and keep a reference to its Job")
        val job = GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        job.join() // wait until child coroutine complete
    }

    fun launchCoroutine() = runBlocking { // this: CoroutineScope
        launch {
            println("launch a new coroutine in the scope of runBlocking")
            delay(1000L)
            println("World!")
        }
        println("Hello,")
    }

    fun scopingCoroutine() = runBlocking { // this: CoroutineScope
        launch {
            delay(200L)
            println("Task from runBlocking")
        }

        coroutineScope {
            println("Creates a coroutine scope")
            launch {
                delay(500L)
                println("Task from nested launch")
            }

            delay(100L)
            println("Task from coroutine scope") // This line will be printed before the nested launch
        }

        println("Coroutine scope is over") // This line is not printed until the nested launch completes
    }

    fun suspending() = runBlocking {
        launch {
            doWorld()
        }
        println("Hello,")
    }

    // this is your first suspending function
    suspend fun doWorld() {
        delay(1000L)
        println("World!")
    }

    fun launchManyManyCoroutines() = runBlocking {
        println("launch a lot of coroutines")
        repeat(100_000) {
            launch {
                delay(1000L)
                print(".")
            }
        }
    }
}
