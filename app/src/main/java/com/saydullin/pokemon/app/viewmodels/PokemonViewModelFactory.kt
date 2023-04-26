package com.saydullin.pokemon.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saydullin.pokemon.domain.usecases.GetPokemonUseCase
import javax.inject.Inject

class PokemonViewModelFactory @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return PokemonViewModel(
            getPokemonUseCase = getPokemonUseCase
        ) as T
    }

}