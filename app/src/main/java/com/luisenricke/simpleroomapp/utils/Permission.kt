package com.luisenricke.simpleroomapp.utils

import android.content.ContentResolver
import android.content.Intent

interface Permission {
    var NAME: String
    var CODE_PERMISSION : Int
    var CODE_REQUEST: Int
    fun config() : Intent
    fun success(contentResolver: ContentResolver, data: Intent?)

/*
    var request: Int = -1
    var permission: Int = -1

    fun trigger() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            when (permission) {
                Constraints.PERMISSION_READ_STORAGE -> {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED
                    ) {
                        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                        requestPermissions(permissions, Constraints.PERMISSION_READ_STORAGE)
                        return
                    }
                    intentImage()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isEmpty() || grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Timber.e("Permision denied")
            return
        }

        when (requestCode) {
            Constraints.PERMISSION_READ_STORAGE -> intentImage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (request != Activity.RESULT_OK) {
            Timber.e("Something fail")
            return
        }

        when (requestCode) {
            Constraints.REQUEST_SELECT_IMAGE_IN_GALLERY -> {
                try {
                    val image: Bitmap =
                        MediaStore.Images.Media.getBitmap(contentResolver, data?.data)
                } catch (e: IOException) {
                    Timber.e(e)
                }
            }
        }
    }

    private fun intentImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Constraints.REQUEST_SELECT_IMAGE_IN_GALLERY)
    }
    */
 */

}