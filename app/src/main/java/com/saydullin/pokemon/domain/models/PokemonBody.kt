package com.saydullin.pokemon.domain.models

import com.saydullin.pokemon.data.db.entities.PokemonBodyEntity

data class PokemonBody(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: ArrayList<Pokemon>
) {

    fun toPokemonBodyEntity() = PokemonBodyEntity(
        count = count,
        next = next,
        previous = previous ?: "",
        pokemonsList = results
    )

}