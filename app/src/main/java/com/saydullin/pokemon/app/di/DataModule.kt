package com.saydullin.pokemon.app.di

import com.saydullin.pokemon.api.services.GetPokemonInfoService
import com.saydullin.pokemon.api.services.GetPokemonsService
import com.saydullin.pokemon.data.db.dao.PokemonDao
import com.saydullin.pokemon.data.db.dao.PokemonInfoDao
import com.saydullin.pokemon.data.repositories.PokemonInfoRepositoryImpl
import com.saydullin.pokemon.data.repositories.PokemonRepositoryImpl
import com.saydullin.pokemon.domain.repositories.PokemonInfoRepository
import com.saydullin.pokemon.domain.repositories.PokemonRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun providePokemonRepositoryImpl(
        pokemonsService: GetPokemonsService,
        pokemonDao: PokemonDao,
    ): PokemonRepository {
        return PokemonRepositoryImpl(pokemonsService, pokemonDao)
    }

    @Provides
    fun providePokemonInfoRepositoryImpl(
        pokemonInfoService: GetPokemonInfoService,
        pokemonInfoDao: PokemonInfoDao,
    ): PokemonInfoRepository {
        return PokemonInfoRepositoryImpl(pokemonInfoService, pokemonInfoDao)
    }

}