package com.saydullin.pokemon.app.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saydullin.pokemon.domain.models.Pokemon
import com.saydullin.pokemon.domain.models.PokemonInfo
import com.saydullin.pokemon.domain.usecases.GetPokemonInfoUseCase
import com.saydullin.pokemon.domain.usecases.GetPokemonUseCase
import com.saydullin.pokemon.domain.utils.Resource
import com.saydullin.pokemon.domain.utils.StatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val getPokemonInfoUseCase: GetPokemonInfoUseCase,
) : ViewModel() {

    val pokemon: MutableState<List<Pokemon>> = mutableStateOf(listOf())
    val pokemonInfo: MutableState<PokemonInfo?> = mutableStateOf(null)
    val loading: MutableState<Boolean?> = mutableStateOf(null)
    val error = MutableLiveData<StatusCode?>(null)
    val isOffline: MutableState<Boolean> = mutableStateOf(false)

    fun getPokemonInfo(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loading.value = true
            val pokemonInfoRequest = getPokemonInfoUseCase.getPokemonInfoFromAPI(url)

            if (pokemonInfoRequest is Resource.Success && pokemonInfoRequest.data != null) {
                pokemonInfo.value = pokemonInfoRequest.data
                getPokemonInfoUseCase.savePokemonInfoDB(pokemonInfoRequest.data, url)
                isOffline.value = false
            } else {
                if (pokemonInfoRequest.statusCode == StatusCode.CONNECTION_ERROR) {
                    isOffline.value = true
                    getPokemonInfoDB(url)
                } else {
                    error.postValue(pokemonInfoRequest.statusCode)
                }
            }
            loading.value = false
        }
    }

    fun getPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            loading.value = true
            val pokemonRequest = getPokemonUseCase.getPokemonsAPI()

            if (pokemonRequest is Resource.Success && pokemonRequest.data != null) {
                pokemon.value = pokemonRequest.data.results
                getPokemonUseCase.savePokemonsDB(pokemonRequest.data)
                isOffline.value = false
            } else {
                if (pokemonRequest.statusCode == StatusCode.CONNECTION_ERROR) {
                    isOffline.value = true
                    getPokemonsDB()
                } else {
                    error.postValue(pokemonRequest.statusCode)
                }
            }
            loading.value = false
        }
    }

    private fun getPokemonInfoDB(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonInfoRequest = getPokemonInfoUseCase.getPokemonInfoFromDB(url)

            if (pokemonInfoRequest is Resource.Success && pokemonInfoRequest.data != null) {
                pokemonInfo.value = pokemonInfoRequest.data
            } else {
                error.postValue(pokemonInfoRequest.statusCode)
            }
        }
    }

    private fun getPokemonsDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonRequest = getPokemonUseCase.getPokemonsDB()

            if (pokemonRequest is Resource.Success && pokemonRequest.data != null) {
                pokemon.value = pokemonRequest.data.results
            } else {
                error.postValue(pokemonRequest.statusCode)
            }
        }
    }

}


