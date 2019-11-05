package com.luisenricke.simpleroomapp.utils

import android.content.ContentResolver
import android.content.Intent

interface Permission {
    var manifest: MutableCollection<String>
    var token: Int
    var requestCode: Int
    fun config(): Intent
    fun success(contentResolver: ContentResolver, data: Intent?)
}