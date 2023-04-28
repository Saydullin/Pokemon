package com.saydullin.pokemon.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saydullin.pokemon.data.db.entities.PokemonRemoteKeysEntity

@Dao
interface PokemonRemoteKeysDao {

    @Query("SELECT * FROM PokemonRemoteKeysEntity WHERE name = :name")
    suspend fun getRemoteKeys(name: String): PokemonRemoteKeysEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKeys(remoteKeys: List<PokemonRemoteKeysEntity>)

    @Query("DELETE FROM PokemonRemoteKeysEntity")
    suspend fun deleteRemoteKeys()

}