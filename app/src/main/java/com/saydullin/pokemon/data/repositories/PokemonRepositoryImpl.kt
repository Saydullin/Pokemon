package com.saydullin.pokemon.data.repositories

import com.saydullin.pokemon.api.services.GetPokemonsService
import com.saydullin.pokemon.data.db.dao.PokemonDao
import com.saydullin.pokemon.domain.models.PokemonBody
import com.saydullin.pokemon.domain.repositories.PokemonRepository
import com.saydullin.pokemon.domain.utils.Resource
import com.saydullin.pokemon.domain.utils.StatusCode
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonsService: GetPokemonsService,
    private val pokemonDao: PokemonDao
) : PokemonRepository {

    override suspend fun getPokemonsAPI(): Resource<PokemonBody> {
        try {
            val pokemons = pokemonsService.getPokemons()
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

    override suspend fun getPokemonsDB(): Resource<PokemonBody> {
        val pokemonBody = pokemonDao.getPokemonsBody()
            ?: return Resource.Error(StatusCode.DATA_NOT_FOUND)

        return Resource.Success(pokemonBody.toPokemonBody())
    }

    override suspend fun savePokemonsDB(pokemonBody: PokemonBody) {
        pokemonDao.savePokemonsBody(pokemonBody.toPokemonBodyEntity())
    }

}


