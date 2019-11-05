package com.luisenricke.simpleroomapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.luisenricke.simpleroomapp.data.AppDatabase
import com.luisenricke.simpleroomapp.utils.Permission
import com.luisenricke.simpleroomapp.utils.PermissionCameraImp
import com.luisenricke.simpleroomapp.utils.PermissionImageGalleryImp
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.name
    }

    private lateinit var db: AppDatabase

    private lateinit var camara: Permission
    private lateinit var gallery: Permission

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getInstance(this)

        gallery = PermissionImageGalleryImp()

        camara = PermissionCameraImp()

        /*
        OkHttpHelper.downloadImage(Constraints.IMAGE_URL_EVANGELION, db.cacheDAO())
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                Timber.i("Before -> Rows: ${db.cacheDAO().count()}")
                val image = db.cacheDAO().pop()?.src?.let { ImageHelper.byteArraytoBitmap(it) }
                img_db.setImageBitmap(image)
                Timber.i("After -> Rows: ${db.cacheDAO().count()}")
            }.doOnError { Timber.e(it) }
            .subscribe()
         */

        btn_gallery.setOnClickListener {
            checkAndroidVersion()
            /*permission = gallery
            if (!isHasPermission(permission.manifest.toTypedArray()))
                askPermission(permission.requestCode, permission.manifest.toTypedArray())
            else
                startActivityForResult(permission.config(), permission.requestCode)
            */
            //showPictureDialog()
        }
    }

    private fun checkAndroidVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission()

        } else {
            // write your logic here
        }

    }

    @SuppressLint("NewApi")
    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) + ContextCompat
                    .checkSelfPermission(
                        this,
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) || ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.CAMERA
                    )) {

                Snackbar.make(
                    this.findViewById(android.R.id.content),
                    "Please Grant Permissions to upload profile photo",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction(
                    "ENABLE"
                ) {
                    requestPermissions(
                        arrayOf(
                            Manifest.permission
                                .READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA
                        ),
                        Constraints.REQUEST_IMAGE_GALLERY
                    )
                }.show()
            } else {
                requestPermissions(
                    arrayOf(
                        Manifest.permission
                            .READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA
                    ),
                    Constraints.REQUEST_IMAGE_GALLERY
                )
            }
        } else {
            // write your logic code if permission already granted
        }
    }

    @SuppressLint("NewApi")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ){

        when (requestCode) {
            Constraints.REQUEST_IMAGE_GALLERY -> if (grantResults.size > 0) {
                val cameraPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED
                val readExternalFile = grantResults[0] == PackageManager.PERMISSION_GRANTED

                if (cameraPermission && readExternalFile) {
                    // write your logic here
                } else {
                    Snackbar.make(
                        this.findViewById(android.R.id.content),
                        "Please Grant Permissions to upload profile photo",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction(
                        "ENABLE"
                    ) {
                        requestPermissions(
                            arrayOf(
                                Manifest.permission
                                    .READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA
                            ),
                            Constraints.REQUEST_IMAGE_GALLERY
                        )
                    }.show()
                }
            }
        }
    }
}
