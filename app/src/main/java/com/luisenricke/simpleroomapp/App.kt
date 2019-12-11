package com.luisenricke.simpleroomapp

import android.app.Application
import android.graphics.BitmapFactory
import com.luisenricke.simpleroomapp.data.AppDatabase
import com.luisenricke.simpleroomapp.data.entity.Image
import com.luisenricke.simpleroomapp.utils.ImageHelper
import timber.log.Timber

class App : Application() {
    private lateinit var manageDB: AppDatabase
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        manageDB = AppDatabase.getInstance(this)

        if (manageDB.imageDAO().count() <= 0) {
            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.img_test)
            val byteArray = ImageHelper.bitmaptoByteArray(bitmap)
            val row = Image("Tes", byteArray)
            Timber.i("row: $row")
            manageDB.imageDAO().insert(row)
        }
    }
}