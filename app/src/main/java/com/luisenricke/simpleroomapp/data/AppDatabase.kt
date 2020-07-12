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

@Database(
    entities = [
        Contact::class,
        Image::class,
        User::class,
        Pet::class,
        Medicine::class,
        PetMedicine::class
    ],
    version = 1, exportSchema = false
)
@TypeConverters(Date::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDAO(): ContactDAO
    abstract fun imageDAO(): ImageDAO
    abstract fun user(): UserDAO
    abstract fun pet(): PetDAO
    abstract fun medicine(): MedicineDAO
    abstract fun petMedicine(): PetMedicineDAO

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
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        ioThread { getInstance(context).contactDAO().insert(INIT_DATA) }

                    }

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)

                    }
                })
                .allowMainThreadQueries() // Uncomment if threads available in MainThread
                .build()
        }

        val INIT_DATA = Contact("root@root.com", "root")
    }
}
