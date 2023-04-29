package com.saydullin.pokemon.domain.repositories

import com.saydullin.pokemon.domain.models.PokemonInfo
import com.saydullin.pokemon.domain.utils.Resource

interface PokemonInfoRepository {

    suspend fun getPokemonInfoAPI(url: String): Resource<PokemonInfo>

    suspend fun getPokemonInfoDB(url: String): Resource<PokemonInfo>

    suspend fun savePokemonInfoDB(pokemonInfo: PokemonInfo, url: String)

}