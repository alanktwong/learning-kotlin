package org.awong.koans.coroutines

import kotlinx.coroutines.*

class CoroutineBasics {
    fun firstCoroutine() = runBlocking<Unit> { // start main coroutine
        GlobalScope.launch { // launch a new coroutine in background and continue
            delay(1000L)
            println("World!")
        }
        println("Hello,") // main coroutine continues here immediately
        delay(2000L)      // delaying for 2 seconds to keep JVM alive
    }
}
