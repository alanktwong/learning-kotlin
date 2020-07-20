package org.awong.koans.coroutines

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertTrue

class CoroutineContextTest {

    val context = CoroutineContext()

    @Test
    fun testLaunchCoroutines() = runBlocking {
        context.launchCoroutinesWithContext()
        assertTrue(true, "Ran coroutines")
    }


    @Test
    fun testConfinedVsUnconfined() = runBlocking {
        context.confinedVersusUnconfinedDispatchers()
        assertTrue(true, "Ran coroutines")
    }

    @Test
    fun testLogging() = runBlocking {
        context.loggingCoroutineContext()
        assertTrue(true, "Ran coroutines")
    }

    @Test
    fun testJumpingBetweenThreads() = runBlocking {
        context.jumpBetweenThreads()
        assertTrue(true, "Ran coroutines")
    }

    @Test
    fun testChildrenOfCoroutine() = runBlocking {
        context.childrenOfCoroutine()
        assertTrue(true, "Ran coroutines")
    }

    @Test
    fun testParentCoroutine() = runBlocking {
        context.parentCoroutine()
        assertTrue(true, "Ran coroutines")
    }

    @Test
    fun testNamedCoroutines() = runBlocking {
        context.namedCoroutines()
        assertTrue(true, "Ran coroutines")
    }

    @Test
    fun testCombiningContextElements() = runBlocking {
        context.combiningContextElements()
        assertTrue(true, "Ran coroutines")
    }
}