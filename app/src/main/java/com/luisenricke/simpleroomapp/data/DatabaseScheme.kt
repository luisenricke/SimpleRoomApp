package com.luisenricke.simpleroomapp.data

interface DatabaseScheme {
    companion object {
        const val NAME = "Contacts_DB"
    }

    interface Contact {
        companion object {
            const val TABLE = "Contact"
            const val ID = "id"
            const val EMAIL = "email"
            const val NAME = "name"
        }
    }
}