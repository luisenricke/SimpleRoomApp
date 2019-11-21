package com.luisenricke.simpleroomapp.data_kotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.luisenricke.simpleroomapp.data_kotlin.cache.Cache
import com.luisenricke.simpleroomapp.data_kotlin.cache.CacheDAO
import com.luisenricke.simpleroomapp.data_kotlin.contact.Contact
import com.luisenricke.simpleroomapp.data_kotlin.contact.ContactDAO
import com.luisenricke.simpleroomapp.data_kotlin.image.Image
import com.luisenricke.simpleroomapp.data_kotlin.image.ImageDAO
import com.luisenricke.simpleroomapp.utils.ioThread

@Database(
    entities = [
        Contact::class,
        Image::class,
        Cache::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDAO(): ContactDAO
    abstract fun imageDAO(): ImageDAO
    abstract fun cacheDAO(): CacheDAO

    companion object {
        const val NAME = "Database.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context)
                    .also { INSTANCE = it }
            }
        }

        fun open(context: Context): AppDatabase {
            return getInstance(context)
        }

        fun close() {
            if (INSTANCE?.isOpen == true) {
                INSTANCE?.close()
            }
            INSTANCE = null
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, NAME)
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) { //When is created do....
                        super.onCreate(db)
                        ioThread {
                            getInstance(context).contactDAO().insert(INIT_DATA)
                        }
                    }

                    override fun onOpen(db: SupportSQLiteDatabase) { //When is open do...
                        super.onOpen(db)

                    }
                })
                .allowMainThreadQueries() // Uncomment if threads available in MainThread
                .build()
        }

        val INIT_DATA = Contact("root@root.com", "root")
    }
}