package ru.psu.mobile.myhome.model

import java.time.LocalDateTime
import java.util.UUID

data class CMessage(
    var id                                  : UUID,
    var text                                : String,
    var dateTime                            : LocalDateTime
                                            = LocalDateTime.now(),
    var author                              : String
                                            = ""
)
