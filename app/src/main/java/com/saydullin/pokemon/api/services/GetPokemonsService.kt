package com.saydullin.pokemon.api.services

import com.saydullin.pokemon.domain.models.PokemonBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GetPokemonsService {

    @GET("pokemon")
    suspend fun getPokemons(): Response<PokemonBody>

    // TODO Change result model
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: String): PokemonBody
}