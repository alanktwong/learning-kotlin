package org.awong.koans.coroutines

import kotlinx.coroutines.runBlocking
import kotlin.test.assertTrue
import org.junit.Test

class AsyncFlowTest {
    private val flow = AsyncFlow()

    @Test
    fun testComputeSequentially() = runBlocking {
        flow.computeSequentially()
        assertTrue(true, "Compute sequentially")
    }

    @Test
    fun testSuspendComputeSequentially() = runBlocking {
        flow.suspendComputeSequentially()
        assertTrue(true, "Compute sequentially")
    }

    @Test
    fun testSimpleFlow() = runBlocking {
        flow.launchSimpleFlow()
        assertTrue(true, "Use a simple reactive flow")
    }

    @Test
    fun testColdFlow() = runBlocking {
        flow.launchColdFlow()
        assertTrue(true, "Use a simple reactive flow which is a cold stream")
    }
}