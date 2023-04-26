package com.saydullin.pokemon.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saydullin.pokemon.data.db.converters.PokemonListConverter
import com.saydullin.pokemon.data.db.converters.PokemonTypeListConverter
import com.saydullin.pokemon.data.db.dao.PokemonDao
import com.saydullin.pokemon.data.db.entities.PokemonEntity
import com.saydullin.pokemon.data.db.entities.PokemonInfoEntity

@Database(
    entities = [
        PokemonEntity::class,
        PokemonInfoEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(
    PokemonTypeListConverter::class,
    PokemonListConverter::class,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

}