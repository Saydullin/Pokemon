package com.saydullin.pokemon.data.repositories

import com.saydullin.pokemon.data.api.services.GetPokemonInfoService
import com.saydullin.pokemon.data.db.dao.PokemonInfoDao
import com.saydullin.pokemon.domain.models.PokemonInfo
import com.saydullin.pokemon.domain.repositories.PokemonInfoRepository
import com.saydullin.pokemon.domain.utils.Resource
import com.saydullin.pokemon.domain.utils.StatusCode
import javax.inject.Inject

class PokemonInfoRepositoryImpl @Inject constructor(
    private val pokemonInfoService: GetPokemonInfoService,
    private val pokemonInfoDao: PokemonInfoDao
) : PokemonInfoRepository {

    override suspend fun getPokemonInfoAPI(url: String): Resource<PokemonInfo> {
        try {
            val pokemons = pokemonInfoService.getPokemonInfo(url)
            val pokemonsBody = pokemons.body()
            if (pokemons.isSuccessful && pokemonsBody != null) {
                return Resource.Success(pokemonsBody)
            }

            return Resource.Error(
                statusCode = StatusCode.CONNECTION_ERROR
            )
        } catch (e: Exception) {
            return Resource.Error(
                statusCode = StatusCode.CONNECTION_ERROR
            )
        }

    }

    override suspend fun getPokemonInfoDB(url: String): Resource<PokemonInfo> {
        val pokemon = pokemonInfoDao.getPokemonInfoByUrl(url)
            ?: return Resource.Error(StatusCode.DATA_NOT_FOUND)

        return Resource.Success(pokemon.toPokemonInfo())
    }

    override suspend fun savePokemonInfoDB(pokemonInfo: PokemonInfo, url: String) {
        pokemonInfoDao.savePokemonInfo(pokemonInfo.toPokemonInfoEntity(url))
    }

}


