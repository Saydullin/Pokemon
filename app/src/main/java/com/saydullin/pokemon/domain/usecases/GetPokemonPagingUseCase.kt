package com.saydullin.pokemon.domain.usecases

import androidx.paging.PagingData
import com.saydullin.pokemon.data.db.entities.PokemonEntity
import com.saydullin.pokemon.domain.repositories.PokemonPagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonPagingUseCase @Inject constructor(
    private val pokemonPagingRepository: PokemonPagingRepository
) {

    fun getAllPokemons(): Flow<PagingData<PokemonEntity>> {
        return pokemonPagingRepository.getAllPokemons()
    }

}


