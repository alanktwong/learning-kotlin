package org.awong.koans.coroutines

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertTrue

class CoroutineBasicsTest {

    val basics = CoroutineBasics()

    @Test
    fun testFirstCoroutine() = runBlocking<Unit> {
        // here we can use suspending functions using any assertion style that we like
        basics.firstCoroutine()
        assertTrue(true, "Ran 1st coroutine")
    }

    @Test
    fun testJoinCoroutine() = runBlocking<Unit> {
        // here we can use suspending functions using any assertion style that we like
        basics.joinCoroutine()
        assertTrue(true, "Ran join coroutine")
    }
}