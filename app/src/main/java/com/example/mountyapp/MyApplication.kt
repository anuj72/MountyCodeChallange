package com.example.mountyapp

import android.app.Application
import com.example.mountyapp.repository.MyRepository
import com.example.mountyapp.networking.MyApi
import com.example.mountyapp.networking.NetworkConnectionInterceptor
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication:Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { MyRepository(instance()) }
        bind() from provider { MainViewModelFactory( instance())}


    }

}