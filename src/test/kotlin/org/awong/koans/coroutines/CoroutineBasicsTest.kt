package org.awong.koans.coroutines

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertTrue

class CoroutineBasicsTest {

    private val basics = CoroutineBasics()

    @Test
    fun testFirstCoroutine() = runBlocking {
        basics.firstCoroutine()
        assertTrue(true, "Ran 1st coroutine")
    }

    @Test
    fun testJoinCoroutine() = runBlocking {
        basics.joinCoroutine()
        assertTrue(true, "Ran join coroutine")
    }

    @Test
    fun testLaunchCoroutine() = runBlocking {
        basics.launchCoroutine()
        assertTrue(true, "Ran launch coroutine")
    }

    @Test
    fun testScopingCoroutine() = runBlocking {
        basics.scopingCoroutine()
        assertTrue(true, "Ran coroutines with multiple scopes")
    }

    @Test
    fun testSuspending() = runBlocking {
        basics.suspending()
        assertTrue(true, "Ran coroutines with suspending function")
    }

    @Test
    fun testLaunchManyManyCoroutines () = runBlocking {
        basics.launchManyManyCoroutines()
        assertTrue(true, "Ran many many coroutines")
    }
}