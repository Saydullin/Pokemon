package com.saydullin.pokemon.data.repositories

import com.saydullin.pokemon.api.services.GetPokemonsService
import com.saydullin.pokemon.data.db.dao.PokemonDao
import com.saydullin.pokemon.domain.models.Pokemon
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
        val pokemons = pokemonsService.getPokemons()
        val pokemonsBody = pokemons.body()
        if (pokemons.isSuccessful && pokemonsBody != null) {
            return Resource.Success(pokemonsBody)
        }

        return Resource.Error(
            statusCode = StatusCode.CONNECTION_ERROR
        )
    }

    override suspend fun getPokemonsDB(): Resource<ArrayList<Pokemon>> {
        val pokemons = pokemonDao.getPokemons().map { it.toPokemon() } as ArrayList<Pokemon>

        return Resource.Success(pokemons)
    }

}


