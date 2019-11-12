package com.luisenricke.simpleroomapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import timber.log.Timber
import java.io.*

object ImageHelper {

    // IS -> Internal Storage
    // https://stackoverflow.com/a/15718526

    fun bitmaptoByteArray(bitmap: Bitmap): ByteArray? {
        val blob = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* Ignored for PNGs */, blob)
        return blob.toByteArray()
    }

    fun byteArraytoBitmap(byteArray: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    fun saveIS(context: Context, image: Bitmap?, name: String) {
        if (image == null) return

        var fos: FileOutputStream? = null
        try {
            fos = context.openFileOutput(name, Context.MODE_PRIVATE)
            image.compress(Bitmap.CompressFormat.PNG, 0, fos)
        } catch (e: FileNotFoundException) {
            Timber.wtf("File not found in SAVE")
            e.printStackTrace()
        } catch (e: IOException) {
            Timber.wtf("IO Exception")
            e.printStackTrace()
        } finally {
            fos?.close()
        }
    }

    fun loadIS(context: Context, name: String): Bitmap? {
        var image: Bitmap? = null
        var fis: FileInputStream? = null
        try {
            fis = context.openFileInput(name)
            image = BitmapFactory.decodeStream(fis)
        } catch (e: FileNotFoundException) {
            Timber.wtf("File not found in LOAD")
            e.printStackTrace()
        } catch (e: IOException) {
            Timber.wtf("IO Exception")
            e.printStackTrace()
        } finally {
            fis?.close()
        }
        return image
    }

    fun deleteIS(context: Context, name: String): Boolean {
        return context.deleteFile(name)
    }
}