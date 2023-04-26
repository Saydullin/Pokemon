package com.saydullin.pokemon.data.db.entities

import androidx.room.*
import com.saydullin.pokemon.data.db.converters.PokemonTypeListConverter
import com.saydullin.pokemon.domain.models.PokemonInfo
import com.saydullin.pokemon.domain.models.PokemonInfoSprite
import com.saydullin.pokemon.domain.models.PokemonType

@Entity
data class PokemonInfoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val url: String,
    @TypeConverters(PokemonTypeListConverter::class) val types: List<PokemonType>,
    @ColumnInfo val frontImage: String
) {

    fun toPokemonInfo() = PokemonInfo(
        id = id,
        name = name,
        url = url,
        types = types,
        sprites = PokemonInfoSprite(
            front_default = frontImage
        )
    )

}


