package org.awong

class Koan {
    fun joinOptions(options: Collection<String>) = options.joinToString(
            prefix = "[",
            postfix = "]")

    fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false) =
            (if (toUpperCase) name.toUpperCase() else name) + number

    fun useFoo() = listOf(
            foo("a"),
            foo("b", number = 1),
            foo("c", toUpperCase = true),
            foo(name = "d", number = 2, toUpperCase = true)
    )

    //https://play.kotlinlang.org/koans/Introduction/Lambdas/Task.kt
}