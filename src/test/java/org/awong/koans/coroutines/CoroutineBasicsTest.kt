package org.awong.koans.coroutines

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertTrue

class CoroutineBasicsTest {

    val basics = CoroutineBasics()

    @Test
    fun testMySuspendingFunction() = runBlocking<Unit> {
        // here we can use suspending functions using any assertion style that we like
        basics.firstCoroutine()
        assertTrue(true, "Ran 1st coroutine")
    }
}