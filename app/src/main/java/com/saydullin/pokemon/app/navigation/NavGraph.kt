package com.saydullin.pokemon.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saydullin.pokemon.app.screens.MainScreen
import com.saydullin.pokemon.app.screens.PokemonInfoScreen
import com.saydullin.pokemon.app.viewmodels.PokemonViewModel

@Composable
fun SetupNavGraph(navController: NavHostController, pokemonViewModel: PokemonViewModel) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController = navController, pokemonViewModel = pokemonViewModel)
        }
        composable(route = Screen.PokemonInfo.route) {
            PokemonInfoScreen(navController = navController, pokemonViewModel = pokemonViewModel)
        }
    }
}