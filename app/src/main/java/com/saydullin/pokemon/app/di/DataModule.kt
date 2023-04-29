package com.saydullin.pokemon.app.di

import androidx.paging.ExperimentalPagingApi
import com.saydullin.pokemon.data.api.services.GetPokemonInfoService
import com.saydullin.pokemon.data.api.services.GetPokemonPagingService
import com.saydullin.pokemon.data.db.AppDatabase
import com.saydullin.pokemon.data.db.dao.PokemonInfoDao
import com.saydullin.pokemon.data.repositories.PokemonInfoRepositoryImpl
import com.saydullin.pokemon.data.repositories.PokemonPagingRepositoryImpl
import com.saydullin.pokemon.domain.repositories.PokemonInfoRepository
import com.saydullin.pokemon.domain.repositories.PokemonPagingRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun providePokemonInfoRepositoryImpl(
        pokemonInfoService: GetPokemonInfoService,
        pokemonInfoDao: PokemonInfoDao,
    ): PokemonInfoRepository {
        return PokemonInfoRepositoryImpl(pokemonInfoService, pokemonInfoDao)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    fun providePokemonPagingRepository(
        getPokemonPagingService: GetPokemonPagingService,
        pokemonDatabase: AppDatabase,
    ): PokemonPagingRepository {
        return PokemonPagingRepositoryImpl(getPokemonPagingService, pokemonDatabase)
    }

}