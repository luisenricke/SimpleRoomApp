package com.luisenricke.simpleroomapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.luisenricke.room.converter.Date
import com.luisenricke.room.ioThread
import com.luisenricke.simpleroomapp.data.dao.*
import com.luisenricke.simpleroomapp.data.entity.*
import timber.log.Timber

@Database(entities = [Contact::class], version = 1, exportSchema = false)
@TypeConverters(Date::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDAO(): ContactDAO

    companion object {
        private const val NAME = "Database.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) { INSTANCE ?: build(context).also { INSTANCE = it } }
        }

        fun close() {
            if (INSTANCE?.isOpen == true) {
                INSTANCE?.close()
            }

            INSTANCE = null
        }

        private fun build(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, NAME)
                .createFromAsset("ExternalDatabase.db")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        ioThread {
                            Timber.i("Start migration")
                            db.execSQL(MIGRATE_CONTACT_TABLE)
                            db.execSQL("DROP TABLE Contact_")
                            Timber.i("Finish migration")
                        }
                    }

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)

                    }
                })
                .allowMainThreadQueries() // Uncomment if threads available in MainThread
                .build()
        }

        val MIGRATE_CONTACT_TABLE =
            """
                INSERT INTO ${Contact.SCHEMA.TABLE} ( 
                    ${Contact.SCHEMA.EMAIL},
                    ${Contact.SCHEMA.NAME},
                    ${Contact.SCHEMA.ID}) 
                SELECT 
                    email_,
                    name_,
                    id_
                FROM Contact_
            """.trimIndent()

    }
}
