package org.awong.koans

import java.util.HashMap
import kotlin.collections.HashMap

// https://play.kotlinlang.org/koans/Builders/Function%20literals%20with%20receiver/Task.kt

/**
 * Read about function literals with receiver.
 * You can declare isEven and isOdd as values, that can be called as
 * extension functions. Complete the declarations below.
 */


fun task(): List<Boolean> {
    val isEven: Int.() -> Boolean = { (this % 2) == 0 }
    val isOdd: Int.() -> Boolean = { (this % 2) != 0  }

    return listOf(42.isOdd(), 239.isOdd(), 294823098.isEven())
}

// https://play.kotlinlang.org/koans/Builders/String%20and%20map%20builders/Task.kt

/**
 * Add and implement the function 'buildMap' with one parameter (of type extension function)
 * creating a new HashMap, building it and returning it as a result.
 * The usage of this function is shown below.
 */

fun buildString(build: StringBuilder.() -> Unit): String {
    val stringBuilder = StringBuilder()
    stringBuilder.build()
    return stringBuilder.toString()
}

val s = buildString {
    this.append("Numbers: ")
    for (i in 1..3) {
        // 'this' can be omitted
        append(i)
    }
}

// assert s == "Numbers: 123"

fun <K, V> buildMap(build: HashMap<K, V>.() -> Unit): Map<K,V> {
    val map = HashMap<K, V>()
    map.build()
    return map
}


fun usage(): Map<Int, String> {
    return buildMap {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}

// https://play.kotlinlang.org/koans/Builders/The%20function%20apply/Task.kt

fun <T> T.myApply(f: T.() -> Unit): T { TODO() }

// https://play.kotlinlang.org/koans/Builders/The%20function%20apply/Task.kt
