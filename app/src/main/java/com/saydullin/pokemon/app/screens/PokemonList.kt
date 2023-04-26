package com.saydullin.pokemon.app.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.saydullin.pokemon.app.activities.PokemonInfoActivity
import com.saydullin.pokemon.app.viewmodels.PokemonViewModel
import com.saydullin.pokemon.domain.models.Pokemon

@Composable
fun PokemonList(pokemonViewModel: PokemonViewModel) {

    val context = LocalContext.current
    val pokemonsList = pokemonViewModel.pokemon.value

    val onClickPokemon = { pokemon: Pokemon ->
        pokemonViewModel.getPokemonInfo(pokemon.url)
        val intent = Intent(context, PokemonInfoActivity::class.java)
        intent.putExtra("pokemon_url", pokemon.url)
        context.startActivity(intent)
        Toast.makeText(context, "Clicked ${pokemon.name}", Toast.LENGTH_SHORT).show()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        itemsIndexed(pokemonsList) { _, item ->
            PokemonItem(pokemon = item, onClick = onClickPokemon)
        }
    }

}