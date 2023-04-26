package com.saydullin.pokemon.data.db.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.saydullin.pokemon.domain.models.Pokemon

@ProvidedTypeConverter
class PokemonListConverter {

    @TypeConverter
    fun fromPokemonListToString(pokemonsList: List<Pokemon>?): String? {
        return Gson().toJson(pokemonsList)
    }

    @TypeConverter
    fun fromStringToPokemonList(pokemonsJSON: String): List<Pokemon>? {
        val listType = object: TypeToken<List<Pokemon>>(){}.type
        return Gson().fromJson(pokemonsJSON, listType)
    }

}