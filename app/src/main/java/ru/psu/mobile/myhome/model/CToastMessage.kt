package ru.psu.mobile.myhome.model

import java.util.UUID

data class CToastMessage (
    val string: String,
    val id : UUID = UUID.randomUUID()
)