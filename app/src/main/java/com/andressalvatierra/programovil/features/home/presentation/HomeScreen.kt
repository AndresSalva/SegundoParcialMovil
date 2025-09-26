package com.andressalvatierra.programovil.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andressalvatierra.programovil.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Menú Principal",
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(32.dp))

        HomeButton(text = "Dólar en Tiempo Real") {
            navController.navigate(Screen.Dollar.route)
        }
        Spacer(modifier = Modifier.height(16.dp))

        HomeButton(text = "Películas Populares") {
            navController.navigate(Screen.PopularMovies.route)
        }
        Spacer(modifier = Modifier.height(16.dp))

        HomeButton(text = "Perfil de Usuario") {
            navController.navigate(Screen.Profile.route)
        }
        Spacer(modifier = Modifier.height(16.dp))

        HomeButton(text = "Buscar en Github") {
            navController.navigate(Screen.Github.route)
        }
    }
}

@Composable
private fun HomeButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(280.dp)
    ) {
        Text(text = text)
    }
}