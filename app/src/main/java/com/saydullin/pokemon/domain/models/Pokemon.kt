package com.saydullin.pokemon.domain.models

import com.saydullin.pokemon.data.db.entities.PokemonEntity

data class Pokemon(
    val id: Int,
    val name: String,
    val url: String,
) {

    companion object {
        val empty = Pokemon(
            id = -1,
            name = "",
            url = ""
        )
    }

    fun toPokemonEntity() = PokemonEntity(
        id = id,
        name = name,
        url = url
    )

}


