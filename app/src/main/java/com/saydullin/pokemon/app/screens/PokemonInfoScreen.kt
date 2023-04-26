package com.saydullin.pokemon.app.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saydullin.pokemon.app.ui.theme.TextOnPrimary
import com.saydullin.pokemon.app.viewmodels.PokemonViewModel

@Composable
fun PokemonInfoScreen(pokemonViewModel: PokemonViewModel) {

    val pokemonInfo = pokemonViewModel.pokemonInfo.value

    Log.e("sady", "PokemonInfoScreen ${pokemonInfo}")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TextOnPrimary)
    ) {
        if (pokemonInfo == null) {
            Text(
                text = "No data",
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            Text(
                text = pokemonInfo.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = pokemonInfo.weight.toString(),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = pokemonInfo.height.toString(),
                style = MaterialTheme.typography.titleLarge
            )
            LazyColumn(
                modifier = Modifier.fillMaxHeight()
            ) {
                itemsIndexed(pokemonInfo.types) { _, item ->
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = item.type.name,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}


