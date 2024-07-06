package com.store.cloths

import android.app.Application
import com.store.cloths.data.db.Cloths

class App : Application(){
    val mainModule by lazy { MainModule(this) }

    override fun onCreate() {
        super.onCreate()

    }
}