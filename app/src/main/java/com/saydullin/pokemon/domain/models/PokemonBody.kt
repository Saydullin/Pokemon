package com.saydullin.pokemon.domain.models

data class PokemonBody(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: ArrayList<Pokemon>
)