package com.saydullin.pokemon.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saydullin.pokemon.data.db.entities.PokemonInfoEntity

@Dao
interface PokemonInfoDao {

    @Query("SELECT * FROM PokemonInfoEntity WHERE url = :url")
    fun getPokemonInfoByUrl(url: String): PokemonInfoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePokemonInfo(pokemonInfoEntity: PokemonInfoEntity)

}


