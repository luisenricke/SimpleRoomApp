package com.luisenricke.simpleroomapp

import android.app.Application
import android.graphics.BitmapFactory
import com.luisenricke.simpleroomapp.data.AppDatabase
import com.luisenricke.simpleroomapp.data.image.Image
import com.luisenricke.simpleroomapp.utils.ImageHelper
import timber.log.Timber

class SimpleRoomApp : Application() {
    private lateinit var manageDB: AppDatabase
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        manageDB = AppDatabase.open(this)

        if (manageDB.imageDAO().count() <= 0) {
            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.img_test)
            val byteArray = ImageHelper.bitmaptoByteArray(bitmap)
            val row = Image("Tes", byteArray, 1)
            manageDB.imageDAO().insert(row)
        }
    }
}