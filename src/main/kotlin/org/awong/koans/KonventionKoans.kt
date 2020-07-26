package org.awong.koans

import java.time.LocalDate

// https://play.kotlinlang.org/koans/Conventions/Comparison/Task.kt

data class YourDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<YourDate> {
    override fun compareTo(other: YourDate): Int {
        val date = LocalDate.of(this.year, this.month, this.dayOfMonth)
        val otherDate = LocalDate.of(other.year, other.month, other.dayOfMonth)
        return date.compareTo(otherDate)
    }
}

fun compare(date1: YourDate, date2: YourDate) = date1 < date2

fun compare(date1: MyDate, date2: MyDate) = date1 < date2

// https://play.kotlinlang.org/koans/Conventions/In%20range/Task.kt
class OldDateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(other: MyDate): Boolean = start <= other && other <= endInclusive
}

fun checkInOldRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in OldDateRange(first, last)
}

// https://play.kotlinlang.org/koans/Conventions/Range%20to/Task.kt
// https://play.kotlinlang.org/koans/Conventions/For%20loop/Task.kt

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = DateIterator(this)
}

enum class  TimeInterval {
    DAY,
    WEEK,
    MONTH,
    YEAR
}

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var current: MyDate = dateRange.start
    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }
    override fun hasNext(): Boolean = current <= dateRange.endInclusive
}

fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in first..last
}

// https://play.kotlinlang.org/koans/Conventions/Operators%20overloading/Task.kt
fun task1(today: MyDate): MyDate {
    return today + TimeInterval.YEAR + TimeInterval.WEEK
}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Long)
operator fun TimeInterval.times(number: Long) = RepeatedTimeInterval(this, number)

fun task2(today: MyDate): MyDate = today + TimeInterval.YEAR * 2 + TimeInterval.WEEK * 3 + TimeInterval.DAY * 5

// https://play.kotlinlang.org/koans/Conventions/Destructuring%20declarations/Task.kt

fun isLeapDay(date: MyDate): Boolean {

    val (year, month, dayOfMonth) = date

    // 29 February of a leap year
    return year % 4 == 0 && month == 2 && dayOfMonth == 29
}

// https://play.kotlinlang.org/koans/Conventions/Invoke/Task.kt

class Invokable {
    var numberOfInvocations: Int = 0
        private set
    operator fun invoke(): Invokable {
        numberOfInvocations = numberOfInvocations + 1
        return this
    }
}

fun invokeTwice(invokable: Invokable) = invokable()()
