package com.saydullin.pokemon.domain.repositories

import com.saydullin.pokemon.domain.models.PokemonBody
import com.saydullin.pokemon.domain.utils.Resource

interface PokemonRepository {

    suspend fun getPokemonsAPI(): Resource<PokemonBody>

    suspend fun getPokemonsDB(): Resource<PokemonBody>

    suspend fun savePokemonsDB(pokemonBody: PokemonBody)

}


