package com.saydullin.pokemon.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val baseUrl = "https://pokeapi.co/api/v2/"

    fun getInstance(): Retrofit {

        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

}