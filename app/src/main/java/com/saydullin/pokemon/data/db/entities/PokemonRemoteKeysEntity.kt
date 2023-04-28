package com.saydullin.pokemon.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonRemoteKeysEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val name: String,
    @ColumnInfo val prevPage: Int?,
    @ColumnInfo val nextPage: Int?,
)