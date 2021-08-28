package com.example.myshootinrangediary.repository

import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

data class Entry (
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val startDate: LocalDateTime,
    val gunLicense: String,
    val endDate: LocalDateTime? = null,
    val weapons: ArrayList<Weapon>? = ArrayList(),
)