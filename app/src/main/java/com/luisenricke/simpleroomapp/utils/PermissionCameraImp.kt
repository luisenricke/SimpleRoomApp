package com.luisenricke.simpleroomapp.utils

import android.Manifest
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import com.luisenricke.simpleroomapp.Constraints
import timber.log.Timber

class PermissionCameraImp(
    override var manifest: MutableCollection<String> = arrayListOf(Manifest.permission.CAMERA),
    override var token: Int = Constraints.PERMISSION_CAMERA,
    override var requestCode: Int = Constraints.REQUEST_CAMERA
) : Permission {
    override fun config(): Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

    override fun success(contentResolver: ContentResolver, data: Intent?) {
        try {
            val image: Bitmap = data?.extras?.get("data") as Bitmap
            Timber.i("${image.byteCount}")
        } catch (e: NullPointerException) {
            Timber.e(e)
        }
    }
}