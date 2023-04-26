package com.saydullin.pokemon.api.services

import com.saydullin.pokemon.domain.models.PokemonBody
import retrofit2.Response
import retrofit2.http.GET

interface GetPokemonsService {

    @GET("pokemon")
    suspend fun getPokemons(): Response<PokemonBody>

}