package com.saydullin.pokemon.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.saydullin.pokemon.data.db.entities.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonEntity")
    fun getPokemons(): List<PokemonEntity>

}