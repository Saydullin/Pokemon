package com.saydullin.pokemon.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saydullin.pokemon.domain.models.Pokemon
import com.saydullin.pokemon.app.ui.theme.TextOnPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonItem(pokemon: Pokemon, onClick: (pokemon: Pokemon) -> Unit) {

    Card(
        modifier = Modifier
            .background(TextOnPrimary)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        shape = RoundedCornerShape(15.dp),
        onClick = {
            onClick(pokemon)
        }
    ) {
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            text = pokemon.name,
            style = MaterialTheme.typography.labelSmall
        )
    }

}


