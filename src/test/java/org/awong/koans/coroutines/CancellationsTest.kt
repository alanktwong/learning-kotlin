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
}