package com.luisenricke.simpleroomapp.data

interface Database {
    interface Contact {
        companion object {
            const val TABLE = "Contact"
            const val ID = "id"
            const val EMAIL = "email"
            const val NAME = "name"
        }
    }
}