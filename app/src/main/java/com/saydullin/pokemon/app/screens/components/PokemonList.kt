package com.saydullin.pokemon.app.screens.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.saydullin.pokemon.app.navigation.Screen
import com.saydullin.pokemon.app.ui.theme.TextOnPrimary
import com.saydullin.pokemon.app.utils.StatusText
import com.saydullin.pokemon.app.viewmodels.PokemonViewModel
import com.saydullin.pokemon.domain.models.Pokemon
import com.saydullin.pokemon.domain.utils.StatusCode

@Composable
fun PokemonList(navController: NavController, pokemonViewModel: PokemonViewModel) {

    val allPokemons = pokemonViewModel.getAllPokemons.collectAsLazyPagingItems()
    val loadState = allPokemons.loadState
    val context = LocalContext.current
    val loading = pokemonViewModel.pokemonLoading.value

    val onClickPokemon = { pokemon: Pokemon ->
        pokemonViewModel.getPokemonInfo(pokemon.url)
        navController.navigate(Screen.PokemonInfo.route)
    }

    if (loading == true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(TextOnPrimary),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
        return
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            items = allPokemons,
            key = { it.name }
        ) {
            PokemonItem(
                pokemon = it?.toPokemon() ?: Pokemon.empty,
                onClick = onClickPokemon
            )
        }
        if (loadState.refresh is LoadState.Loading
            || loadState.append is LoadState.Loading
            || loadState.prepend is LoadState.Loading
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        if (loadState.refresh is LoadState.Error
            || loadState.append is LoadState.Error
            || loadState.prepend is LoadState.Error) {
            val statusText = StatusText(context, StatusCode.CONNECTION_ERROR)
            Toast.makeText(context, statusText.getCaption(), Toast.LENGTH_SHORT).show()
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = { allPokemons.refresh() }) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }

}