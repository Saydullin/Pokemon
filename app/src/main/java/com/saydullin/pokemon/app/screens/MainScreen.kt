package com.saydullin.pokemon.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.saydullin.pokemon.R
import com.saydullin.pokemon.app.ui.theme.TextOnPrimary
import com.saydullin.pokemon.app.viewmodels.PokemonViewModel

@Composable
fun MainScreen(pokemonViewModel: PokemonViewModel) {

    val context = LocalContext.current
    val appName = context.getString(R.string.app_name)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TextOnPrimary)
    ) {
        HeaderView(appName, pokemonViewModel)
        PokemonList(pokemonViewModel)
    }

}