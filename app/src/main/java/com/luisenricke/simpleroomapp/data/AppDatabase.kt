package com.luisenricke.simpleroomapp

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDAO(): ContactDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        fun openDB(context: Context): ContactDAO {
            return getInstance(context).contactDAO()
        }

        fun closeDB() {
            if (INSTANCE?.isOpen == true) {
                INSTANCE?.close()
            }
            INSTANCE = null
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "CONTACS_DB.db")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) { //When is created do....
                        super.onCreate(db)
                        ioThread {
                            getInstance(context).contactDAO().insert(INIT_DATA)
                        }
                    }

                    override fun onOpen(db: SupportSQLiteDatabase) { //When id open do...
                        super.onOpen(db)

                    }
                })
                .allowMainThreadQueries() // Uncomment if threads available in MainThread
                .build()
        }

        val INIT_DATA = Contact("root@root.com", "root")
    }
}