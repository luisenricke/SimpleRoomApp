package com.luisenricke.simpleroomapp

import android.app.Application
import com.luisenricke.simpleroomapp.data.AppDatabase
import timber.log.Timber

class App : Application() {

    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        initTimber()
        initDatabase()
    }

    private fun initDatabase() {
        database = AppDatabase.getInstance(this)
        if (database.contactDAO().count() <= 0) Timber.i("Empty database")
        else Timber.i("Contact count: ${database.contactDAO().count()}")
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
