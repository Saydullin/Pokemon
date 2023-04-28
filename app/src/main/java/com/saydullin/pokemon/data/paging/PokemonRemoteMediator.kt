package com.saydullin.pokemon.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.saydullin.pokemon.data.api.services.GetPokemonPagingService
import com.saydullin.pokemon.data.db.AppDatabase
import com.saydullin.pokemon.data.db.entities.PokemonEntity
import com.saydullin.pokemon.data.db.entities.PokemonRemoteKeysEntity
import com.saydullin.pokemon.data.utils.Constants.ITEMS_PER_PAGE
import javax.inject.Inject

@ExperimentalPagingApi
class PokemonRemoteMediator @Inject constructor(
    private val getPokemonPagingService: GetPokemonPagingService,
    private val pokemonDatabase: AppDatabase,
): RemoteMediator<Int, PokemonEntity>() {

    private val pokemonDao = pokemonDatabase.pokemonItemDao()
    private val pokemonRemoteKeysDao = pokemonDatabase.pokemonRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = getPokemonPagingService.getPokemons(
                offset = currentPage * ITEMS_PER_PAGE - ITEMS_PER_PAGE,
                limit = ITEMS_PER_PAGE
            )
            val endOfPaginationReached = response.next == null

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            pokemonDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pokemonDao.deleteAllPokemons()
                    pokemonRemoteKeysDao.deleteRemoteKeys()
                }
                val keys = response.results.map { pokemonItem ->
                    PokemonRemoteKeysEntity(
                        id = pokemonItem.id,
                        name = pokemonItem.name,
                        nextPage = nextPage,
                        prevPage = prevPage
                    )
                }
                pokemonRemoteKeysDao.addRemoteKeys(remoteKeys = keys)
                pokemonDao.addPokemons(pokemons = response.results.map { it.toPokemonEntity() })
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }

    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PokemonEntity>
    ): PokemonRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.name?.let { name ->
                pokemonRemoteKeysDao.getRemoteKeys(name = name)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, PokemonEntity>
    ): PokemonRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                pokemonRemoteKeysDao.getRemoteKeys(name = unsplashImage.name)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PokemonEntity>
    ): PokemonRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                pokemonRemoteKeysDao.getRemoteKeys(name = unsplashImage.name)
            }
    }

}