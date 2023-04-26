package com.saydullin.pokemon.app.di

import com.saydullin.pokemon.api.RetrofitBuilder
import com.saydullin.pokemon.api.services.GetPokemonInfoService
import com.saydullin.pokemon.api.services.GetPokemonsService
import dagger.Module
import dagger.Provides
import retrofit2.create

@Module
class NetworkModule {

    @Provides
    fun providePokemonService(): GetPokemonsService {
        val retrofit = RetrofitBuilder.getInstance()
        return retrofit.create()
    }

    @Provides
    fun providePokemonInfoService(): GetPokemonInfoService {
        val retrofit = RetrofitBuilder.getInstance()
        return retrofit.create()
    }

}