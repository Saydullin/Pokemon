package com.saydullin.pokemon.domain.usecases

import com.saydullin.pokemon.domain.models.PokemonInfo
import com.saydullin.pokemon.domain.repositories.PokemonInfoRepository
import com.saydullin.pokemon.domain.utils.Resource
import javax.inject.Inject

class GetPokemonInfoUseCase @Inject constructor(
    private val pokemonInfoRepository: PokemonInfoRepository
) {

    suspend fun getPokemonInfoFromAPI(url: String): Resource<PokemonInfo> {
        return pokemonInfoRepository.getPokemonInfoAPI(url)
    }

    suspend fun getPokemonInfoFromDB(url: String): Resource<PokemonInfo> {
        return pokemonInfoRepository.getPokemonInfoDB(url)
    }

    suspend fun savePokemonInfoDB(pokemonInfo: PokemonInfo, url: String) {
        pokemonInfoRepository.savePokemonInfoDB(pokemonInfo, url)
    }

}


