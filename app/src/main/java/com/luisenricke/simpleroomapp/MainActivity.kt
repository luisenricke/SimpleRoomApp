package com.luisenricke.simpleroomapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luisenricke.simpleroomapp.data.AppDatabase
import com.luisenricke.simpleroomapp.data.contact.Contact
import com.luisenricke.simpleroomapp.utils.OkHttpHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.name
    }

    private lateinit var list: List<Contact>
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = AppDatabase.getInstance(this)

        val urlimage =
            "https://proxy.duckduckgo.com/iu/?u=https%3A%2F%2Fi.ytimg.com%2Fvi%2FjrTMMG0zJyI%2Fmaxresdefault.jpg"
        val urlimage2 =
            "https://proxy.duckduckgo.com/iu/?u=https%3A%2F%2Fwallup.net%2Fwp-content%2Fuploads%2F2016%2F05%2F14%2F45171-Samurai_Champloo-Mugen.jpg"

        OkHttpHelper.imageCacheObservable(urlimage, database.cacheDAO())
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(OkHttpHelper.imageCacheObserver(database.cacheDAO()))
    }
}
