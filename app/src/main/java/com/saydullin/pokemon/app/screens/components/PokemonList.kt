package com.saydullin.pokemon.app.screens.components

import androidx.compose.foundation.layout.*
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
import com.saydullin.pokemon.R
import com.saydullin.pokemon.app.viewmodels.PokemonViewModel
import com.saydullin.pokemon.domain.models.Pokemon
import com.saydullin.pokemon.domain.utils.StatusCode

@Composable
fun PokemonList(navController: NavController, pokemonViewModel: PokemonViewModel) {

    val allPokemons = pokemonViewModel.getAllPokemons.collectAsLazyPagingItems()
    val loadState = allPokemons.loadState
    val context = LocalContext.current

    val onClickPokemon = { pokemon: Pokemon ->
        pokemonViewModel.getPokemonInfo(pokemon.url)
        navController.navigate(Screen.PokemonInfo.route)
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
        ) {
            pokemonViewModel.setError(null)
            pokemonViewModel.setOffline(false)
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
        if (loadState.refresh is LoadState.Error ||
            loadState.append is LoadState.Error) {
            pokemonViewModel.setError(StatusCode.CONNECTION_ERROR)
            pokemonViewModel.setOffline(true)
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Button(onClick = { allPokemons.retry() }) {
                        Text(text = context.getString(R.string.try_again))
                    }
                }
            }
        }
    }

}