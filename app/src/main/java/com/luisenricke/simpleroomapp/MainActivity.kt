package com.luisenricke.simpleroomapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.luisenricke.simpleroomapp.data.AppDatabase
import com.luisenricke.simpleroomapp.utils.OpenGalleryPermission
import com.luisenricke.simpleroomapp.utils.Permission
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.name
    }

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getInstance(this)

        perm = OpenGalleryPermission(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Constraints.PERMISSION_READ_STORAGE,
            Constraints.REQUEST_SELECT_IMAGE_IN_GALLERY
        )

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
            if (!isHasPermission(perm.NAME))
                askPermission(perm.CODE_REQUEST, perm.NAME)
            else
                startActivityForResult(perm.config(), perm.CODE_REQUEST)
        }
    }
}
