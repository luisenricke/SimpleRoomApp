package com.luisenricke.simpleroomapp

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.luisenricke.simpleroomapp.adapter.ContactAdapter
import com.luisenricke.simpleroomapp.data.entity.Contact
import com.luisenricke.simpleroomapp.databinding.ActivityMainBinding
import com.luisenricke.simpleroomapp.utils.ImageHelper

class MainActivity : AppCompatActivity() {

    private val list = arrayListOf(
        Contact("test@test.com", "test", 1),
        Contact("luis@luis.com", "luis", 2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            lblTitle.text = "Binding text title"

            recyclerContacts.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this.context)
                adapter = ContactAdapter(list) { item ->
                    Toast.makeText(applicationContext, "clicked ${item.name}", Toast.LENGTH_SHORT).show()
                }
            }
        }


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
