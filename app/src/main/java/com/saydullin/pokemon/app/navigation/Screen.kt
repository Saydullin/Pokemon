package com.saydullin.pokemon.app.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object PokemonInfo: Screen("pokemon_info_screen")
}


