package com.saydullin.pokemon.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.saydullin.pokemon.app.viewmodels.PokemonViewModel

@Composable
fun MainScreen(pokemonViewModel: PokemonViewModel) {

    Column {
        HeaderView(pokemonViewModel)
        PokemonList(pokemonViewModel)
    }

}