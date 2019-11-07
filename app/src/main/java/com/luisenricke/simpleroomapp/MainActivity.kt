package com.luisenricke.simpleroomapp

import android.os.Bundle
import com.luisenricke.simpleroomapp.data.AppDatabase
import com.luisenricke.simpleroomapp.utils.Permission
import com.luisenricke.simpleroomapp.utils.PermissionCameraImp
import com.luisenricke.simpleroomapp.utils.PermissionImageGalleryImp
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

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
            checkPermission(gallery)
        }
    }
}
