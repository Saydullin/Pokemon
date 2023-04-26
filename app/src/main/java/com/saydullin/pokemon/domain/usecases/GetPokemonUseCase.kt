package com.saydullin.pokemon.domain.usecases

import com.saydullin.pokemon.domain.models.Pokemon
import com.saydullin.pokemon.domain.models.PokemonBody
import com.saydullin.pokemon.domain.repositories.PokemonRepository
import com.saydullin.pokemon.domain.utils.Resource
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    suspend fun getPokemonsFromAPI(): Resource<PokemonBody> {
        return pokemonRepository.getPokemonsAPI()
    }

    suspend fun getPokemonsFromDB(): Resource<ArrayList<Pokemon>> {
        return pokemonRepository.getPokemonsDB()
    }

}