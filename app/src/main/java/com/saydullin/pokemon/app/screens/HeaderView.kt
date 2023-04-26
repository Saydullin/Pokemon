package com.saydullin.pokemon.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saydullin.pokemon.app.ui.theme.Primary
import com.saydullin.pokemon.app.ui.theme.TextOnPrimary
import com.saydullin.pokemon.app.viewmodels.PokemonViewModel

@Composable
fun HeaderView(pokemonViewModel: PokemonViewModel) {

    val isOffline = pokemonViewModel.isOffline.value

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Primary)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Pokemon App",
                fontSize = 20.sp,
                style = MaterialTheme.typography.titleLarge,
                color = TextOnPrimary
            )

            if (isOffline) {
                Text(
                    text = "You are offline",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextOnPrimary
                )
            }

        }
    }

}


