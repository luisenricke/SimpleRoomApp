package com.luisenricke.simpleroomapp.utils

import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import timber.log.Timber
import java.io.IOException

class OpenGalleryPermission(
    override var NAME: String,
    override var CODE_PERMISSION: Int,
    override var CODE_REQUEST: Int
) : Permission {

    override fun config(): Intent = Intent(Intent.ACTION_PICK).setType("image/*")

    override fun success(contentResolver: ContentResolver, data: Intent?) {
        try {
            val image: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data?.data)
            Timber.i("${image.byteCount}")
        } catch (e: IOException) {
            Timber.e(e)
        }
    }
}