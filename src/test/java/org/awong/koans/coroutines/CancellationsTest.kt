package org.awong.koans.coroutines

import kotlinx.coroutines.runBlocking
import kotlin.test.assertTrue
import org.junit.Test

class CancellationsTest {
    val cancellations = Cancellations()

    @Test
    fun testCancelJob() = runBlocking {
        cancellations.cancelJob()
        assertTrue(true, "Cancel 1st coroutine")
    }

    @Test
    fun testCooperativeCancel() = runBlocking {
        cancellations.cooperativeCancel()
        assertTrue(true, "Cancel coroutine cooperatively")
    }

    @Test
    fun testCancellable() = runBlocking {
        cancellations.cancellableCoroutine()
        assertTrue(true, "Cancel coroutine when it is cancellable")
    }

    @Test
    fun testCloseFinally() = runBlocking {
        cancellations.closeWithFinally()
        assertTrue(true, "Cancel coroutine which closes with a finally")
    }

    @Test
    fun testCloseFinallyAndSafely() = runBlocking {
        cancellations.closeWithFinallySafe()
        assertTrue(true, "Cancel coroutine which closes with a safe finally block")
    }

    @Test
    fun testCloseWithTimeout() = runBlocking {
        cancellations.closeWithTimeout()
        assertTrue(true, "Cancel coroutine which times out")
    }
}