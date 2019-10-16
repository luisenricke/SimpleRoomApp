package com.luisenricke.simpleroomapp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

object ImageHelper {
    fun bitmaptoByteArray(bitmap: Bitmap): ByteArray? {
        val blob = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* Ignored for PNGs */, blob)
        return blob.toByteArray()
    }

    fun byteArraytoBitmap(byteArray: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}