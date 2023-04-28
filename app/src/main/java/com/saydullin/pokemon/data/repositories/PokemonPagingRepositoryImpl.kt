package com.saydullin.pokemon.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.saydullin.pokemon.data.api.services.GetPokemonPagingService
import com.saydullin.pokemon.data.db.AppDatabase
import com.saydullin.pokemon.data.db.entities.PokemonEntity
import com.saydullin.pokemon.data.paging.PokemonRemoteMediator
import com.saydullin.pokemon.data.utils.Constants.ITEMS_PER_PAGE
import com.saydullin.pokemon.domain.repositories.PokemonPagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class PokemonPagingRepositoryImpl @Inject constructor(
    private val getPokemonPagingService: GetPokemonPagingService,
    private val pokemonDatabase: AppDatabase,
) : PokemonPagingRepository {

    override fun getAllPokemons(): Flow<PagingData<PokemonEntity>> {
        val pagingSourceFactory = {
            pokemonDatabase.pokemonItemDao().getAllPokemons()
        }

        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = PokemonRemoteMediator(
                getPokemonPagingService = getPokemonPagingService,
                pokemonDatabase = pokemonDatabase,
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow

    }

}