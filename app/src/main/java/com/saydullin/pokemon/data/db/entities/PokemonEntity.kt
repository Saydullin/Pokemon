package com.saydullin.pokemon.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.saydullin.pokemon.domain.models.Pokemon

@Entity
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val url: String
) {

    fun toPokemon() = Pokemon(
        id = id,
        name = name,
        url = url
    )

}


