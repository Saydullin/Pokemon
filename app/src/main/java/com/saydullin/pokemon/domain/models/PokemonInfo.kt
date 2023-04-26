package com.saydullin.pokemon.domain.models

import com.saydullin.pokemon.data.db.entities.PokemonInfoEntity

data class PokemonInfo(
    val id: Int,
    val name: String,
    val types: List<PokemonType>,
    val url: String?,
    val weight: Int,
    val height: Int,
    val sprites: PokemonInfoSprite,
) {

    fun toPokemonInfoEntity(urlLink: String) = PokemonInfoEntity(
        id = id,
        name = name,
        weight = weight,
        height = height,
        types = types,
        url = urlLink,
        frontImage = sprites.front_default
    )

}


