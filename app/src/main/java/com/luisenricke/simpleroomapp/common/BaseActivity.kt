package com.luisenricke.simpleroomapp.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luisenricke.simpleroomapp.data.AppDatabase

abstract class BaseActivity : AppCompatActivity() {

    lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = AppDatabase.getInstance(this)
    }
}