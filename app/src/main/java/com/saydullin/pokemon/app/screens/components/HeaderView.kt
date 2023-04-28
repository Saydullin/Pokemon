package com.saydullin.pokemon.app.screens.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.saydullin.pokemon.R
import com.saydullin.pokemon.app.ui.theme.Primary
import com.saydullin.pokemon.app.ui.theme.TextOnPrimary
import com.saydullin.pokemon.app.viewmodels.PokemonViewModel

@Composable
fun HeaderView(
    title: String,
    navController: NavController,
    pokemonViewModel: PokemonViewModel,
    hasBackButton: Boolean = false
) {

    val context = LocalContext.current
    val isOffline = pokemonViewModel.isOffline.value

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Primary)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (hasBackButton) {
                    IconButton(
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp),
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            tint = Color.White,
                            contentDescription = context.getString(R.string.cd_back)
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                }
                Text(
                    text = title,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleLarge,
                    color = TextOnPrimary
                )
            }
            if (isOffline) {
                HeaderOfflineIcon(context)
            }
        }
    }
}

@Composable
fun HeaderOfflineIcon(context: Context) {

    Row() {
        IconButton(
            modifier = Modifier
                .width(25.dp)
                .height(25.dp),
            onClick = {
                Toast.makeText(context, context.getString(R.string.no_connection), Toast.LENGTH_SHORT).show()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_no_connection),
                tint = Color.White,
                contentDescription = context.getString(R.string.cd_back)
            )
        }
    }

}
