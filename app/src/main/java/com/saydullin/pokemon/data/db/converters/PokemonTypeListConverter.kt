package com.saydullin.pokemon.data.db.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.saydullin.pokemon.domain.models.PokemonType

@ProvidedTypeConverter
class PokemonTypeListConverter {

    @TypeConverter
    fun fromPokemonTypeListToString(pokemonTypeList: List<PokemonType>?): String? {
        return Gson().toJson(pokemonTypeList)
    }

    @TypeConverter
    fun fromStringToPokemonTypeList(pokemonTypeList: String): List<PokemonType>? {
        val listType = object: TypeToken<List<PokemonType>>(){}.type
        return Gson().fromJson(pokemonTypeList, listType)
    }

}