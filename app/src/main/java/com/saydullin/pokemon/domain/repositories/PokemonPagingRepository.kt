package com.saydullin.pokemon.domain.repositories

import androidx.paging.PagingData
import com.saydullin.pokemon.data.db.entities.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokemonPagingRepository {

    fun getAllPokemons(): Flow<PagingData<PokemonEntity>>

}