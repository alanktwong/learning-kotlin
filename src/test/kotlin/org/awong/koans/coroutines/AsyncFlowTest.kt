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

    @Test
    fun testZipFlow() = runBlocking {
        flow.zipFlows()
        assertTrue(true, "Zip 2 flows together")

        flow.zipOnEach()
        assertTrue(true, "Zip 2 flows together on each")

        flow.combineFlows()
        assertTrue(true, "Combine 2 flows together")
    }

    @Test
    fun testFlattening() = runBlocking {
        flow.flatMapConcat()
        assertTrue(true, "Flat map concat means wait for the inner flow to complete before starting to collect the next one")

        flow.flatMapMerge()
        assertTrue(true, "Flat map merge means concurrently collect all the incoming flows and merge their values into a single flow so that values are emitted as soon as possible.")

        flow.flatMapLatest()
        assertTrue(true, "Flat map latest means a collection of the previous flow is cancelled as soon as new flow is emitted.")
    }

    @Test
    fun testFlowExceptionHandling() = runBlocking {
        flow.collectTryCatch()
        assertTrue(true, "Collector will try/catch")

        flow.catchEverything()
        assertTrue(true, "Collector will catch everything")

        flow.catchAndThenEmit()
        assertTrue(true, "emit the text on catching an exception")

        flow.catchDeclaratively()
        assertTrue(true, "catch transparently")
    }


    @Test(expected = IllegalStateException::class)
    fun testCatchTransparently() = runBlocking {
        flow.catchTransparently()
        assertTrue(true, "catch transparently")
    }
}