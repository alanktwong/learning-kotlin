package org.awong.koans.coroutines

import kotlinx.coroutines.*

fun main() {
    // launch a new coroutine in background and continue
    GlobalScope.launch {
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}