package com.saydullin.pokemon.domain.models

import com.saydullin.pokemon.data.db.entities.PokemonInfoEntity

data class PokemonInfo(
    val id: Int,
    val name: String,
    val url: String,
    val types: List<PokemonType>,
    val sprites: PokemonInfoSprite,
) {

    fun toPokemonInfoEntity() = PokemonInfoEntity(
        id = id,
        name = name,
        url = url,
        types = types,
        frontImage = sprites.front_default
    )

}


