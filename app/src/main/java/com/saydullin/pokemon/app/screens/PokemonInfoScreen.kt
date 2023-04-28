package com.saydullin.pokemon.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.saydullin.pokemon.R
import com.saydullin.pokemon.app.screens.components.HeaderView
import com.saydullin.pokemon.app.ui.theme.TextOnPrimary
import com.saydullin.pokemon.app.viewmodels.PokemonViewModel

@Composable
fun PokemonInfoScreen(navController: NavController, pokemonViewModel: PokemonViewModel) {

    val context = LocalContext.current
    val pokemonInfo = pokemonViewModel.pokemonInfo.value
    val loading = pokemonViewModel.pokemonInfoLoading.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TextOnPrimary)
    ) {
        HeaderView(
            title = pokemonInfo?.name ?: "",
            navController = navController,
            pokemonViewModel = pokemonViewModel,
            hasBackButton = true
        )
        if (loading == true) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(TextOnPrimary),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }
        if (pokemonInfo == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(TextOnPrimary),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = context.getString(R.string.no_data),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        } else {
            PokemonInfoSContent(pokemonInfo)
        }
    }
}


