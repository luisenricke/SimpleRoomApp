package com.luisenricke.simpleroomapp.utils

import android.graphics.Bitmap
import com.luisenricke.simpleroomapp.data.cache.Cache
import com.luisenricke.simpleroomapp.data.cache.CacheDAO
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.io.IOException


object OkHttpHelper {
    private val client = OkHttpClient()
    private var request: Request? = null

    fun imageCacheObserver(cache: CacheDAO): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: String) {
                Timber.i(t)
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {
                val countRows = cache.count()
                val cache: Bitmap? = cache.get()
                    ?.src?.let { ImageHelper.byteArraytoBitmap(it) }
                Timber.i("Activity2 >> Rows: $countRows")
                Timber.i("DB2 >> Image: ${cache?.byteCount} :: ${cache?.width}x${cache?.height}")
            }
        }
    }

    fun imageCacheObservable(url: String, cache: CacheDAO): Observable<String> {
        return Observable.create<String> {
            val client = OkHttpClient()
            val call: Call = client.newCall(Request.Builder().url(url).get().build())
            val response: Response = call.execute()
            if (!response.isSuccessful) it.onError(IOException("Unexpected code $response"))

            val image = response.body?.bytes()
                ?.let { change -> ImageHelper.byteArraytoBitmap(change) }
            Timber.i("Request >> Image: ${image?.byteCount} :: ${image?.width}x${image?.height}")

            cache.push(Cache(image?.let { convert -> ImageHelper.bitmaptoByteArray(convert) }))
            val cachedImage = cache.get()?.src
                ?.let { convert -> ImageHelper.byteArraytoBitmap(convert) }
            Timber.i("DB >> Image: ${cachedImage?.byteCount} :: ${cachedImage?.width}x${cachedImage?.height}")
            it.onComplete()
        }
    }
}