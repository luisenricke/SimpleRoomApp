package com.luisenricke.simpleroomapp.utils

import com.luisenricke.simpleroomapp.data.dao.ImageDAO
import com.luisenricke.simpleroomapp.data.entity.Image
import io.reactivex.Observable
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object OkHttpHelper {
    private val client = OkHttpClient()

    fun downloadImage(url: String, image: ImageDAO): Observable<String> {
        return Observable.create<String> { emitter ->
            val call: Call = client.newCall(Request.Builder().url(url).get().build())

            val response: Response = call.execute()
            if (!response.isSuccessful) emitter.onError(IOException("Unexpected code $response"))

            val res = response.body?.bytes()?.let { ImageHelper.byteArraytoBitmap(it) }
            image.insert(Image("Downloaded", res?.let { ImageHelper.bitmaptoByteArray(it) }))
            emitter.onComplete()
        }
    }
}