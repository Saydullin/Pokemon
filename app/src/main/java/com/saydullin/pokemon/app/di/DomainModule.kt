package com.saydullin.pokemon.app.di

import com.saydullin.pokemon.domain.repositories.PokemonInfoRepository
import com.saydullin.pokemon.domain.repositories.PokemonPagingRepository
import com.saydullin.pokemon.domain.usecases.GetPokemonInfoUseCase
import com.saydullin.pokemon.domain.usecases.GetPokemonPagingUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providesPokemonInfoUseCase(
        pokemonInfoRepository: PokemonInfoRepository,
    ): GetPokemonInfoUseCase {
        return GetPokemonInfoUseCase(pokemonInfoRepository)
    }

    @Provides
    fun providePokemonPagingUseCase(
        pokemonPagingRepository: PokemonPagingRepository
    ): GetPokemonPagingUseCase {
        return GetPokemonPagingUseCase(pokemonPagingRepository)
    }

}