package com.luisenricke.simpleroomapp

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luisenricke.simpleroomapp.databinding.ActivityMainBinding
import com.luisenricke.simpleroomapp.utils.ImageHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lblTitle.text = "Binding text title"

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

//        existImage()
    }
//
//    fun existImage(){
//        val image: Bitmap? = ImageHelper.loadIS(this, "GalleryImage")
//        img_db.setImageBitmap(image)
//    }
}
