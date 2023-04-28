package com.saydullin.pokemon.data.api.services

import com.saydullin.pokemon.domain.models.PokemonInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface GetPokemonInfoService {

    @GET
    suspend fun getPokemonInfo(@Url url: String): Response<PokemonInfo>

}