package org.awong.koans

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.properties.Delegates


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
class ManualLazyProperty(val initializer: () -> Int) {
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
class LazyProperty(val initializer: () -> Int) {
    val lazyValue: Int by lazy {
        initializer.invoke()
    }
}

// https://play.kotlinlang.org/koans/Properties/Delegates%20how%20it%20works/Task.kt
/**
 *
 * You may declare your own delegates. Implement the methods of the
 * class 'EffectiveDate' so it can be delegated to. Store only the time
 * in milliseconds in 'timeInMillis' property.
 *
 * Use the extension functions MyDate.toMillis() and Long.toDate(), defined at MyDate.kt
 */
fun MyDate.toMillis(): Long {
    return LocalDateTime.of(year, month, dayOfMonth, 0, 0).toInstant(ZoneOffset.UTC).epochSecond
}

fun Long.toDate(): MyDate {
    val localDate = LocalDate.ofInstant(Instant.ofEpochMilli(this), ZoneOffset.UTC)

    return MyDate(localDate.year, localDate.monthValue, localDate.dayOfMonth)
}

class D {
    var date: MyDate by EffectiveDate()
}

class EffectiveDate<R> : ReadWriteProperty<R, MyDate> {

    var timeInMillis: Long? = null

    override fun getValue(thisRef: R, property: KProperty<*>): MyDate {
        return timeInMillis!!.toDate()
    }

    override fun setValue(thisRef: R, property: KProperty<*>, value: MyDate) {
        timeInMillis = value.toMillis()
    }
}