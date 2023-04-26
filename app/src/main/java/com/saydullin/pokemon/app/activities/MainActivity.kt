package com.saydullin.pokemon.app.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.saydullin.pokemon.app.App
import com.saydullin.pokemon.app.viewmodels.PokemonViewModel
import com.saydullin.pokemon.app.viewmodels.PokemonViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var vmFactory: PokemonViewModelFactory
    private lateinit var vmPokemon: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as App).appComponent.inject(this)

        vmPokemon = ViewModelProvider(this, vmFactory)
            .get(PokemonViewModel::class.java)

        vmPokemon.check()

        vmPokemon.pokemon.observe(this) {
            Log.e("sady", "got pokemons list! it has ${it.size} size")
        }

        vmPokemon.error.observe(this) {
            if (it != null) {
                Toast.makeText(this, "Error $it", Toast.LENGTH_SHORT).show()
            }
        }

        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(text = "Hello Saydullin")
            }
        }

    }
}



