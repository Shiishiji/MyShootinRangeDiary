package com.example.myshootinrangediary.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime

object DateTimeConverter
{
    @RequiresApi(Build.VERSION_CODES.O)
    fun dateTimeToString(dateTime: LocalDateTime?): String
    {
        if (null === dateTime) {
            return ""
        }

        val day : String = fillWithZeros(dateTime.dayOfMonth.toString())
        val month : String = fillWithZeros(dateTime.monthValue.toString())
        val year : String = fillWithZeros(dateTime.year.toString())

        val hour : String = fillWithZeros(dateTime.hour.toString())
        val minute : String = fillWithZeros(dateTime.minute.toString())

        return String.format("%s.%s.%s %s:%s", day, month, year, hour, minute)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun dateTimeToDateString(dateTime: LocalDateTime?): String
    {
        if (null === dateTime) {
            return ""
        }

        val day : String = fillWithZeros(dateTime.dayOfMonth.toString())
        val month : String = fillWithZeros(dateTime.monthValue.toString())
        val year : String = fillWithZeros(dateTime.year.toString())

        return String.format("%s.%s.%s", day, month, year)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun dateToDateString(dateTime: LocalDate?): String
    {
        if (null === dateTime) {
            return ""
        }

        val day : String = fillWithZeros(dateTime.dayOfMonth.toString())
        val month : String = fillWithZeros(dateTime.monthValue.toString())
        val year : String = fillWithZeros(dateTime.year.toString())

        return String.format("%s.%s.%s", day, month, year)
    }

    private fun fillWithZeros(value: String): String
    {
        if (1 == value.length) {
            return String.format("0%s", value)
        }

        return value
    }
}