package com.saydullin.pokemon.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saydullin.pokemon.data.db.entities.PokemonBodyEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonBodyEntity")
    fun getPokemonsBody(): PokemonBodyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePokemonsBody(pokemonBodyEntity: PokemonBodyEntity)

}


