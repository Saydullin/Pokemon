package com.saydullin.pokemon.app.di

import com.saydullin.pokemon.MainActivity
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        AppModule::class,
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}

