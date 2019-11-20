package com.luisenricke.simpleroomapp.data_kotlin.contact

import androidx.room.Ignore

//TODO: Check why force to ignore id the compiler
data class ContactMinimal(
    var name: String = "",
    @Ignore
    var id: Long = 0
)