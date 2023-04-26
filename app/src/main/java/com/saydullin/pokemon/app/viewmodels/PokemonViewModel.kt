package com.saydullin.pokemon.app.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saydullin.pokemon.domain.models.Pokemon
import com.saydullin.pokemon.domain.usecases.GetPokemonUseCase
import com.saydullin.pokemon.domain.utils.Resource
import com.saydullin.pokemon.domain.utils.StatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private val errorStatus = MutableLiveData<StatusCode?>(null)
    private val pokemonList = MutableLiveData(listOf<Pokemon>())
    val pokemon = pokemonList
    val error = errorStatus

    fun check() {
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonRequest = getPokemonUseCase.getPokemonsFromAPI()

            if (pokemonRequest is Resource.Success && pokemonRequest.data != null) {
                pokemonList.postValue(pokemonRequest.data.results)
            } else {
                errorStatus.postValue(pokemonRequest.statusCode)
            }
        }
    }

}