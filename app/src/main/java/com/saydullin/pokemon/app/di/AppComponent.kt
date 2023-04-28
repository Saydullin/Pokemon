package com.saydullin.pokemon.app.di

import com.saydullin.pokemon.app.activities.MainActivity
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        AppModule::class,
        DataModule::class,
        DomainModule::class,
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}

