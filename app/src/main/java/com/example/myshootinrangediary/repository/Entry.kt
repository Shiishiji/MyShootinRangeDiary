package com.example.myshootinrangediary.repository

import java.time.LocalDateTime

data class Entry (
    val firstName: String,
    val lastName: String,
    val startDate: LocalDateTime,
    val gunLicense: String,
    val signature: String,
    val endDate: LocalDateTime? = null,
    val weapons: ArrayList<Weapon>? = ArrayList(),
)