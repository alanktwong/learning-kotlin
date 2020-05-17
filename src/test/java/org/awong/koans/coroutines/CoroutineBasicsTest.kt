package org.awong.koans.coroutines

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertTrue

class CoroutineBasicsTest {

    val basics = CoroutineBasics()

    @Test
    fun testFirstCoroutine() = runBlocking {
        // here we can use suspending functions using any assertion style that we like
        basics.firstCoroutine()
        assertTrue(true, "Ran 1st coroutine")
    }

    @Test
    fun testJoinCoroutine() = runBlocking {
        // here we can use suspending functions using any assertion style that we like
        basics.joinCoroutine()
        assertTrue(true, "Ran join coroutine")
    }

    @Test
    fun testLaunchCoroutine() = runBlocking {
        // here we can use suspending functions using any assertion style that we like
        basics.launchCoroutine()
        assertTrue(true, "Ran launch coroutine")
    }
}