package org.awong.koans

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }

    operator fun rangeTo(other: MyDate) = DateRange(this, other)

    fun addTimeIntervals(timeInterval: TimeInterval, number: Long): MyDate {
        val localDate = LocalDate.of(year, month, dayOfMonth)
        val newLocalDate = when (timeInterval) {
            TimeInterval.DAY -> localDate.plusDays(number)
            TimeInterval.WEEK -> localDate.plusWeeks(number)
            TimeInterval.MONTH -> localDate.plusMonths(number)
            TimeInterval.YEAR -> localDate.plusYears(number)
        }
        return MyDate(newLocalDate.year, newLocalDate.monthValue, newLocalDate.dayOfMonth)
    }

    fun nextDay() = addTimeIntervals(TimeInterval.DAY, 1)

    operator fun plus(interval: TimeInterval) = addTimeIntervals(interval, 1)

    operator fun plus(timeIntervals: RepeatedTimeInterval) = addTimeIntervals(timeIntervals.timeInterval, timeIntervals.number)

    fun toMillis(): Long {
        return LocalDateTime.of(year, month, dayOfMonth, 0, 0).toInstant(ZoneOffset.UTC).epochSecond
    }
}


fun Long.toDate(): MyDate {
    val localDate = LocalDate.ofInstant(Instant.ofEpochMilli(this), ZoneOffset.UTC)

    return MyDate(localDate.year, localDate.monthValue, localDate.dayOfMonth)
}

