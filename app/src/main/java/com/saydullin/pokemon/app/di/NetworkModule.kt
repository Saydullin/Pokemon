package com.saydullin.pokemon.app.di

import com.saydullin.pokemon.data.api.RetrofitBuilder
import com.saydullin.pokemon.data.api.services.GetPokemonInfoService
import com.saydullin.pokemon.data.api.services.GetPokemonPagingService
import com.saydullin.pokemon.data.api.services.GetPokemonsService
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

    @Provides
    fun providePokemonPagingService(): GetPokemonPagingService {
        val retrofit = RetrofitBuilder.getInstance()
        return retrofit.create()
    }

}