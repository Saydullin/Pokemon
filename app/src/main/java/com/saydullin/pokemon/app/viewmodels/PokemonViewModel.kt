package com.saydullin.pokemon.app.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saydullin.pokemon.domain.models.Pokemon
import com.saydullin.pokemon.domain.models.PokemonInfo
import com.saydullin.pokemon.domain.usecases.GetPokemonInfoUseCase
import com.saydullin.pokemon.domain.usecases.GetPokemonPagingUseCase
import com.saydullin.pokemon.domain.usecases.GetPokemonUseCase
import com.saydullin.pokemon.domain.utils.Resource
import com.saydullin.pokemon.domain.utils.StatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val getPokemonInfoUseCase: GetPokemonInfoUseCase,
    getPokemonPagingUseCase: GetPokemonPagingUseCase,
) : ViewModel() {

    val pokemon: MutableState<List<Pokemon>> = mutableStateOf(listOf())
    val pokemonInfo: MutableState<PokemonInfo?> = mutableStateOf(null)
    val pokemonLoading: MutableState<Boolean?> = mutableStateOf(null)
    val pokemonInfoLoading: MutableState<Boolean?> = mutableStateOf(null)
    val error = MutableLiveData<StatusCode?>(null)
    val isOffline: MutableState<Boolean> = mutableStateOf(false)
    val getAllPokemons = getPokemonPagingUseCase.getAllPokemons()

    fun retryPokemons() {
        getAllPokemons.retry()
    }

    fun getPokemonInfo(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonInfoLoading.value = true
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
                    pokemonInfo.value = null
                    error.postValue(pokemonInfoRequest.statusCode)
                }
            }
            pokemonInfoLoading.value = false
        }
    }

    fun getPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonLoading.value = true
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
                    pokemon.value = listOf()
                    error.postValue(pokemonRequest.statusCode)
                }
            }
            pokemonLoading.value = false
        }
    }

    private fun getPokemonInfoDB(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonInfoRequest = getPokemonInfoUseCase.getPokemonInfoFromDB(url)

            if (pokemonInfoRequest is Resource.Success && pokemonInfoRequest.data != null) {
                pokemonInfo.value = pokemonInfoRequest.data
            } else {
                pokemonInfo.value = null
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
                pokemon.value = listOf()
                error.postValue(pokemonRequest.statusCode)
            }
        }
    }

}


