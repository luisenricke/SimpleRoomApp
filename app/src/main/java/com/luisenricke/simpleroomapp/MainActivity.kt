package com.luisenricke.simpleroomapp

import android.graphics.Bitmap
import android.os.Bundle
import com.luisenricke.simpleroomapp.common.BaseActivity
import com.luisenricke.simpleroomapp.utils.ImageHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        existImage()
    }

    fun existImage(){
        val image: Bitmap? = ImageHelper.loadIS(this, "GalleryImage")
        img_db.setImageBitmap(image)
    }
}
