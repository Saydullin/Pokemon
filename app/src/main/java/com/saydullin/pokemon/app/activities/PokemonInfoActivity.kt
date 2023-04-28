package com.saydullin.pokemon.app.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.saydullin.pokemon.app.App
import com.saydullin.pokemon.app.screens.PokemonInfoScreen
import com.saydullin.pokemon.app.viewmodels.PokemonViewModel
import com.saydullin.pokemon.app.viewmodels.PokemonViewModelFactory
import com.saydullin.pokemon.app.ui.theme.PokemonTheme
import com.saydullin.pokemon.app.utils.StatusText
import javax.inject.Inject

class PokemonInfoActivity : ComponentActivity() {

    @Inject
    lateinit var vmFactory: PokemonViewModelFactory
    private lateinit var vmPokemon: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as App).appComponent.inject(this)

        vmPokemon = ViewModelProvider(this, vmFactory)[PokemonViewModel::class.java]

        val pokemonUrl = intent.getStringExtra("pokemon_url")

        if (pokemonUrl != null) {
            vmPokemon.getPokemonInfo(pokemonUrl)
        }

        vmPokemon.error.observe(this) { statusCode ->
            if (statusCode != null) {
                val statusText = StatusText(this, statusCode)
                Toast.makeText(this, statusText.getCaption(), Toast.LENGTH_SHORT).show()
            }
        }

        setContent {
            PokemonTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    PokemonInfoScreen(vmPokemon)
                }
            }
        }
    }
}


