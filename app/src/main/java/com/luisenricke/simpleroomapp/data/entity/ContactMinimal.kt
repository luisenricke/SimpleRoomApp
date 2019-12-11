package com.luisenricke.simpleroomapp.data.entity

import androidx.room.Ignore

data class ContactMinimal(
    var name: String = "",
    @Ignore
    var id: Long = 0
)