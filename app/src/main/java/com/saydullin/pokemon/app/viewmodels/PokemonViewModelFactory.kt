package com.saydullin.pokemon.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saydullin.pokemon.domain.usecases.GetPokemonInfoUseCase
import com.saydullin.pokemon.domain.usecases.GetPokemonUseCase
import javax.inject.Inject

class PokemonViewModelFactory @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val getPokemonInfoUseCase: GetPokemonInfoUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return PokemonViewModel(
            getPokemonUseCase = getPokemonUseCase,
            getPokemonInfoUseCase = getPokemonInfoUseCase,
        ) as T
    }

}