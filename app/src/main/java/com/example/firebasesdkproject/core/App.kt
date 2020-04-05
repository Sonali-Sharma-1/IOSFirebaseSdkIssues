package com.example.firebasesdkproject.core

import android.app.Application
import com.facebook.stetho.Stetho
import kotlin.properties.Delegates


class App : Application() {

    companion object{
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Stetho.initializeWithDefaults(this);

    }
//    override fun attachBaseContext(base: Context?) {
//        super.attachBaseContext(base)
//        MultiDex.install(this)
//    }
}