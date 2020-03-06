package org.awong

import org.junit.Assert.assertEquals
import org.junit.Test

class MyLibraryTest {
    @Test
    fun testMyLanguage() {
        val lib = MyLibrary()
        assertEquals("Kotlin", lib.kotlinLanguage().name)
        assertEquals(10, lib.kotlinLanguage().hotness)
        assertEquals("OK", lib.start())
    }
}