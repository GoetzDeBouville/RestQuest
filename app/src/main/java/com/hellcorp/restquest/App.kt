package com.hellcorp.restquest

import android.app.Application
import com.hellcorp.restquest.di.networkModule
import com.hellcorp.restquest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(viewModelModule, networkModule))
        }
    }
}