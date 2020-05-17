package org.awong.koans.coroutines

import kotlinx.coroutines.*

class CoroutineBasics {
    fun firstCoroutine() = runBlocking { // start main coroutine
        GlobalScope.launch { // launch a new coroutine in background and continue
            delay(1000L)
            println("World!")
        }
        println("Hello,") // main coroutine continues here immediately
        delay(2000L)      // delaying for 2 seconds to keep JVM alive
    }

    fun joinCoroutine() = runBlocking  {
        val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        job.join() // wait until child coroutine complete
    }

    fun launchCoroutine() = runBlocking { // this: CoroutineScope
        launch { // launch a new coroutine in the scope of runBlocking
            delay(1000L)
            println("World!")
        }
        println("Hello,")
    }
}
