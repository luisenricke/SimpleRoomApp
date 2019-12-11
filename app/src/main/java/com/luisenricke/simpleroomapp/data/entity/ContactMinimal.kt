package com.luisenricke.simpleroomapp.data.contact

import androidx.room.Ignore

data class ContactMinimal(
    var name: String = "",
    @Ignore
    var id: Long = 0
)