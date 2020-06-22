package com.luisenricke.simpleroomapp

import android.app.Application
import com.luisenricke.simpleroomapp.data.AppDatabase
import timber.log.Timber

class App : Application() {
    private lateinit var manageDB: AppDatabase
    override fun onCreate() {
        super.onCreate()

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
