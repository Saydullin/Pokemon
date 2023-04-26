package com.saydullin.pokemon.app

import android.app.Application
import com.saydullin.pokemon.app.di.AppComponent
import com.saydullin.pokemon.app.di.AppModule
import com.saydullin.pokemon.app.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(mApplicationContext = applicationContext))
            .build()

    }

}