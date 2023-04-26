package com.saydullin.pokemon.app.di

import com.saydullin.pokemon.api.RetrofitBuilder
import com.saydullin.pokemon.api.services.GetPokemonsService
import dagger.Module
import dagger.Provides
import retrofit2.create

@Module
class NetworkModule {

    @Provides
    fun provideProductionPokemonService(): GetPokemonsService {
        val retrofit = RetrofitBuilder.getInstance()
        return retrofit.create()
    }

}