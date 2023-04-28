package com.saydullin.pokemon.data.api.services

import com.saydullin.pokemon.domain.models.PokemonBody
import retrofit2.http.GET
import retrofit2.http.Query

interface GetPokemonPagingService {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): PokemonBody

}