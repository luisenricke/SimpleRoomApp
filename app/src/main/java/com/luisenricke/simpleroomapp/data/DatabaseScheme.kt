package com.luisenricke.simpleroomapp.data

interface DatabaseScheme {
    companion object {
        const val NAME = "Contacts_DB"
        const val CACHE = "Cache_DB"
    }

    interface Contact {
        companion object {
            const val TABLE = "Contact"
            const val ID = "id"
            const val EMAIL = "email"
            const val NAME = "name"
        }
    }

    interface Image {
        companion object {
            const val TABLE = "Image"
            const val ID = "id"
            const val NAME = "name"
            const val SRC = "source"
        }
    }

    interface Cache {
        companion object {
            const val TABLE = "Cache"
            const val ID = "id"
            const val SRC = "source"
        }
    }
}