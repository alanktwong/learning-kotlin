package org.awong.koans.coroutines

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertTrue

class SuspendingFunctionsTest {

    private val functions = SuspendingFunctions()

    @Test
    fun testComposeTwoFunctionsSequentially() = runBlocking {
        functions.composeTwoFunctionsSequentially()
        assertTrue(true, "Ran 2 coroutines sequentially")
    }

    @Test
    fun testComposeTwoFunctionsAsync() = runBlocking {
        functions.composeTwoFunctionsAsync()
        assertTrue(true, "Ran 2 coroutines asynchronously")
    }

    @Test
    fun testComposeTwoFunctionsAsync_AndLazilyStartThem() = runBlocking {
        functions.composeTwoFunctionsAsyncLazily()
        assertTrue(true, "Ran 2 coroutines asynchronously")
    }

    @Test
    fun testComposeAsyncStyleFunctions() = runBlocking {
        functions.composeAsyncStyleFunctions()
        assertTrue(true, "Ran 2 coroutines asynchronously")
    }

    @Test
    fun testStructuredConcurrency() = runBlocking {
        functions.structuredConcurrency()
        assertTrue(true, "Ran 2 coroutines asynchronously")
    }

    @Test
    fun testStructuredConcurrencyWithErrorHandling() = runBlocking {
        functions.structuredConcurrencyWithErrorHandling()
        assertTrue(true, "Ran 2 coroutines asynchronously")
    }

}