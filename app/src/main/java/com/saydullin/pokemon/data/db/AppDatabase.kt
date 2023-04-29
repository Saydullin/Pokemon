package com.saydullin.pokemon.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saydullin.pokemon.data.db.converters.PokemonTypeListConverter
import com.saydullin.pokemon.data.db.dao.PokemonInfoDao
import com.saydullin.pokemon.data.db.dao.PokemonItemDao
import com.saydullin.pokemon.data.db.dao.PokemonRemoteKeysDao
import com.saydullin.pokemon.data.db.entities.*

@Database(
    entities = [
        PokemonEntity::class,
        PokemonInfoEntity::class,
        PokemonRemoteKeysEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(
    PokemonTypeListConverter::class,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonInfoDao(): PokemonInfoDao

    abstract fun pokemonItemDao(): PokemonItemDao

    abstract fun pokemonRemoteKeysDao(): PokemonRemoteKeysDao

}