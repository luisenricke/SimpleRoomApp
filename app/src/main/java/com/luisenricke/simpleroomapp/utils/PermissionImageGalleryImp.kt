package com.luisenricke.simpleroomapp.utils

import android.Manifest
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import com.luisenricke.simpleroomapp.Constraints
import timber.log.Timber
import java.io.IOException

class PermissionImageGalleryImp(
    override var manifest: MutableCollection<String> = arrayListOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ),
    override var token: Int = Constraints.PERMISSION_IMAGE_GALLERY,
    override var requestCode: Int = Constraints.REQUEST_IMAGE_GALLERY
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