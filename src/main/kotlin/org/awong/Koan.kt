package org.awong

import java.util.*
import kotlin.collections.ArrayList

// https://play.kotlinlang.org/koans/Introduction/Named%20arguments/Task.kt
fun joinOptions(options: Collection<String>) = options.joinToString(
        prefix = "[",
        postfix = "]")

// https://play.kotlinlang.org/koans/Introduction/Default%20arguments/Task.kt
fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false) =
        (if (toUpperCase) name.toUpperCase() else name) + number

fun useFoo() = listOf(
        foo("a"),
        foo("b", number = 1),
        foo("c", toUpperCase = true),
        foo(name = "d", number = 2, toUpperCase = true)
)

// https://play.kotlinlang.org/koans/Introduction/Lambdas/Task.kt
fun containsEven(collection: Collection<Int>): Boolean = collection.any {
    each: Int -> each % 2 == 0
}

// https://play.kotlinlang.org/koans/Introduction/Strings/Task.kt

val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

fun getPattern(): String = """\d{2} ${month} \d{4}"""

// https://play.kotlinlang.org/koans/Introduction/Data%20classes/Task.kt
data class Person(val name: String, val age: Int)

fun getPeople(): List<Person> {
    return listOf(Person("Alice", 29), Person("Bob", 31))
}


// https://play.kotlinlang.org/koans/Introduction/Nullable%20types/Task.kt
data class Client (val personalInfo: PersonalInfo?)
data class PersonalInfo (val email: String?)

interface Mailer {
    fun sendMessage(email: String, message: String)
}


fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
    val personalInfo: PersonalInfo? = client?.personalInfo
    val email: String? = personalInfo?.email
    if (email != null && message != null) {
        mailer.sendMessage(email, message)
    }
}

// https://play.kotlinlang.org/koans/Introduction/Smart%20casts/Task.kt
fun eval(expr: Expr): Int =
        when (expr) {
            is Num -> expr.value
            is Sum -> eval(expr.left) + eval(expr.right)
            else -> throw IllegalArgumentException("Unknown expression")
        }

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

// https://play.kotlinlang.org/koans/Introduction/Extension%20functions/Task.kt

fun Int.r(): RationalNumber = RationalNumber(this, 1)
fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(first, second)

data class RationalNumber(val numerator: Int, val denominator: Int)

// https://play.kotlinlang.org/koans/Introduction/Object%20expressions/Task.kt
// https://play.kotlinlang.org/koans/Introduction/SAM%20conversions/Task.kt
fun getList(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    println("Sorting list in descending order")
    Collections.sort(arrayList, { thiz, that -> that-thiz })
    return arrayList
}

fun extendList(): List<Int> {
    return arrayListOf(1, 5, 2).sortedDescending()
}