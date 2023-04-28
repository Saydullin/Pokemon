package com.saydullin.pokemon.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.saydullin.pokemon.R
import com.saydullin.pokemon.domain.models.PokemonInfo

@Composable
fun PokemonInfoSContent(pokemonInfo: PokemonInfo) {

    val context = LocalContext.current
    val pokemonTypesText = pokemonInfo.types.joinToString(", ") { it.type.name }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = rememberAsyncImagePainter(pokemonInfo.sprites.front_default),
            contentDescription = pokemonInfo.name,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = context.getString(R.string.weight_num_kg, pokemonInfo.weight),
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = context.getString(R.string.height_num_cm, pokemonInfo.height),
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = context.getString(R.string.types_str, pokemonTypesText),
            style = MaterialTheme.typography.displayMedium
        )
    }
}


