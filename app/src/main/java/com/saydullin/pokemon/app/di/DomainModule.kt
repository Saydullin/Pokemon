package com.saydullin.pokemon.app.di

import com.saydullin.pokemon.domain.repositories.PokemonInfoRepository
import com.saydullin.pokemon.domain.repositories.PokemonRepository
import com.saydullin.pokemon.domain.usecases.GetPokemonInfoUseCase
import com.saydullin.pokemon.domain.usecases.GetPokemonUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providesPokemonUseCase(
        pokemonRepository: PokemonRepository,
    ): GetPokemonUseCase {
        return GetPokemonUseCase(pokemonRepository)
    }

    @Provides
    fun providesPokemonInfoUseCase(
        pokemonInfoRepository: PokemonInfoRepository,
    ): GetPokemonInfoUseCase {
        return GetPokemonInfoUseCase(pokemonInfoRepository)
    }

}