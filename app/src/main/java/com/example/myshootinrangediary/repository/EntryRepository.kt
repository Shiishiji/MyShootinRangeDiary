package com.example.myshootinrangediary.repository

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.Month

object EntryRepository
{
    val entries: ArrayList<Entry>
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            return arrayListOf(
                Entry("Damian", "Test1",
                    LocalDateTime.of(2021, Month.OCTOBER, 26,12,20),
                    "licence",
                    "signatureString",
                    LocalDateTime.of(2021, Month.OCTOBER, 26,13,0)),
                Entry("Asia", "Test2",
                    LocalDateTime.of(2021, Month.OCTOBER, 26,12,20),
                    "licence",
                    "signatureString",
                    LocalDateTime.of(2021, Month.OCTOBER, 26,13,0)),
                Entry("Marcin", "Test3",
                    LocalDateTime.of(2021, Month.OCTOBER, 26,14,0),
                    "licence",
                    "signatureString",
                    LocalDateTime.of(2021, Month.OCTOBER, 26,15,0)),
                Entry("Paweł", "Test4",
                    LocalDateTime.of(2021, Month.OCTOBER, 26,19,20),
                    "licence",
                    "signatureString"),
            )
        }

    fun addEntry(entry: Entry)
    {
        entries.add(entry)
    }
}