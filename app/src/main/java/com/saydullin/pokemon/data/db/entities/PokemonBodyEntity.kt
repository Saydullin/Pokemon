package com.saydullin.pokemon.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.saydullin.pokemon.data.db.converters.PokemonTypeListConverter
import com.saydullin.pokemon.domain.models.Pokemon
import com.saydullin.pokemon.domain.models.PokemonBody

@Entity
data class PokemonBodyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = -1,
    @ColumnInfo val count: Int,
    @ColumnInfo val next: String,
    @ColumnInfo val previous: String,
    @TypeConverters(PokemonTypeListConverter::class) val pokemonsList: List<Pokemon>,
) {

    fun toPokemonBoy() = PokemonBody(
        count = count,
        next = next,
        previous = previous,
        results = pokemonsList as ArrayList<Pokemon>
    )

}