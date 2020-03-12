package org.awong.koans

// https://play.kotlinlang.org/koans/Properties/Properties/Task.kt
/**
 * Add a custom setter to PropertyExample.propertyWithCounter
 * so that the counter property is incremented every time propertyWithCounter is assigned to.
 */
class PropertyExample() {
    var counter = 0
    var propertyWithCounter: Int? = null
        set(value) {
            field = value
            counter++
        }
}

// https://play.kotlinlang.org/koans/Properties/Lazy%20property/Task.kt

/**
 * Add a custom getter to make the 'lazy' val really lazy. It
 * should be initialized by the invocation of 'initializer()' at
 * the moment of the first access.
 *
 *  You can add as many additional properties as you need.
 *
 *  Do not use delegated properties!
 */
class LazyProperty(val initializer: () -> Int) {
    private var _back: Int? = null
    val lazy: Int
        get() {
            if (_back == null) {
                _back = initializer.invoke()
            }
            return _back ?: throw AssertionError("Set to null by another thread")
        }
}

// https://play.kotlinlang.org/koans/Properties/Delegates%20examples/Task.kt
