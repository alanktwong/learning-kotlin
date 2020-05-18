package org.awong.koans.coroutines

import kotlinx.coroutines.runBlocking
import kotlin.test.assertTrue
import org.junit.Test

class CancellationsTest {
    val cancellations = Cancellations()

    @Test
    fun testFirstCoroutine() = runBlocking {
        cancellations.cancelJob()
        assertTrue(true, "Cancel 1st coroutine")
    }

}