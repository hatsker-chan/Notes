package com.example.notes

import android.app.Application
import com.example.notes.di.DaggerAppComponent

class MyApplication : Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}