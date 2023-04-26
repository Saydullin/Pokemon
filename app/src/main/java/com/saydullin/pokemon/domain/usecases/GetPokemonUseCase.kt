package com.saydullin.pokemon.domain.usecases

import com.saydullin.pokemon.domain.models.PokemonBody
import com.saydullin.pokemon.domain.repositories.PokemonRepository
import com.saydullin.pokemon.domain.utils.Resource
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    suspend fun getPokemonsAPI(): Resource<PokemonBody> {
        return pokemonRepository.getPokemonsAPI()
    }

    suspend fun getPokemonsDB(): Resource<PokemonBody> {
        return pokemonRepository.getPokemonsDB()
    }

    suspend fun savePokemonsDB(pokemonBody: PokemonBody) {
        pokemonRepository.savePokemonsDB(pokemonBody)
    }

}