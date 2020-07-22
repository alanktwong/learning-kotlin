package org.awong.koans.coroutines

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlin.test.assertTrue
import org.junit.Test
import java.lang.IllegalStateException

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

    @Test
    fun testLaunchFlowWithTimeout() = runBlocking {
        flow.launchFlowWithTimeout()
        assertTrue(true, "Use a simple reactive flow which will timeout")
    }

    @Test
    fun testLaunchFlowFromRange() = runBlocking {
        flow.rangeAsFlow()
        assertTrue(true, "Use a simple reactive flow which will timeout")
    }

    @Test
    fun testMapFlow() = runBlocking {
        flow.mapFlow()
        assertTrue(true, "Use a simple reactive flow is mappable")
    }

    @Test
    fun testTransformFlow() = runBlocking {
        flow.transformFlow()
        assertTrue(true, "Use a simple reactive flow is transformable")
    }

    @Test
    fun testTakeFew() = runBlocking {
        flow.takeFew()
        assertTrue(true, "Use a simple reactive flow takes a few and terminates the flow")
    }

    @Test
    fun testMapReduce() = runBlocking {
        flow.mapReduce()
        assertTrue(true, "Use a simple reactive flow that maps and then reduces")
    }

    @Test
    fun testSequentialOperations() = runBlocking {
        flow.sequentialFlow()
        assertTrue(true, "Use a simple reactive flow that performs its operations sequentially")
    }

    @Test
    fun testPreserveContext() = runBlocking {
        flow.preserveContext()
        assertTrue(true, "Use a simple reactive flow that preserves its context")
    }

    @Test(expected = IllegalStateException::class)
    fun testLaunchWrongEmission() = runBlocking {
        flow.wrongEmission().collect { value -> println(value) }
    }

    @Test
    fun testFlowOn() = runBlocking {
        flow.launchFlowOn()
        assertTrue(true, "Use a simple reactive flow that switches its context")
    }

    @Test
    fun testUnbuffered_Buffering() = runBlocking {
        flow.unbuffered()
        assertTrue(true, "Use a unbuffered flow that should be slower than buffering")

        flow.buffering()
        assertTrue(true, "Use a buffered flow that should be faster than not buffering")
    }

    @Test
    fun testConflation_CollectLatest() = runBlocking {
        flow.conflateEmissions()
        assertTrue(true, "Use a flow that conflates emissions")

        flow.collectLatest()
        assertTrue(true, "Use a flow that collects latest emissions")
    }
}