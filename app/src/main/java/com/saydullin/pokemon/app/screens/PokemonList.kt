package com.saydullin.pokemon.app.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.saydullin.pokemon.app.activities.PokemonInfoActivity
import com.saydullin.pokemon.app.ui.theme.TextOnPrimary
import com.saydullin.pokemon.app.viewmodels.PokemonViewModel
import com.saydullin.pokemon.domain.models.Pokemon

@Composable
fun PokemonList(pokemonViewModel: PokemonViewModel) {

    val context = LocalContext.current
    val pokemonsList = pokemonViewModel.pokemon.value
    val loading = pokemonViewModel.loading.value

    val onClickPokemon = { pokemon: Pokemon ->
        val intent = Intent(context, PokemonInfoActivity::class.java)
        intent.putExtra("pokemon_url", pokemon.url)
        context.startActivity(intent)
    }

    if (loading == true) {
        Box(
            modifier = Modifier.fillMaxSize().background(TextOnPrimary),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
        return
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        itemsIndexed(pokemonsList) { _, item ->
            PokemonItem(pokemon = item, onClick = onClickPokemon)
        }
    }

}