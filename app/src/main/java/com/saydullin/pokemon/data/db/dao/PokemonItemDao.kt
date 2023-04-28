package com.saydullin.pokemon.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saydullin.pokemon.data.db.entities.PokemonEntity

@Dao
interface PokemonItemDao {

    @Query("SELECT * FROM PokemonEntity")
    fun getAllPokemons(): PagingSource<Int, PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPokemons(pokemons: List<PokemonEntity>)

    @Query("DELETE FROM PokemonEntity")
    suspend fun deleteAllPokemons()

}