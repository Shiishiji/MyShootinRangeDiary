package com.example.myshootinrangediary.repository

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.Month
import java.util.*
import kotlin.collections.ArrayList

object EntryRepository
{
    val entries: ArrayList<Entry>
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            return arrayListOf(
                Entry(
                    UUID.randomUUID(),
                    "Damian", "Test1",
                    LocalDateTime.of(2021, Month.OCTOBER, 26,12,20),
                    "licence",
                    LocalDateTime.of(2021, Month.OCTOBER, 26,13,0)),
                Entry(
                    UUID.randomUUID(),
                    "Asia", "Test2",
                    LocalDateTime.of(2021, Month.OCTOBER, 26,12,20),
                    "licence",
                    LocalDateTime.of(2021, Month.OCTOBER, 26,13,0)),
                Entry(
                    UUID.randomUUID(),
                    "Marcin", "Test3",
                    LocalDateTime.of(2021, Month.OCTOBER, 26,14,0),
                    "licence",
                    LocalDateTime.of(2021, Month.OCTOBER, 26,15,0)),
                Entry(
                    UUID.randomUUID(),
                    "Paweł", "Test4",
                    LocalDateTime.of(2021, Month.OCTOBER, 26,19,20),
                    "licence"),
            )
        }

    fun addEntry(entry: Entry)
    {
        entries.add(entry)
    }
}