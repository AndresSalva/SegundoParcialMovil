package com.andressalvatierra.programovil.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andressalvatierra.programovil.features.cardexample.presentation.CardScreen
import com.andressalvatierra.programovil.features.dollar.presentation.DollarScreen
import com.andressalvatierra.programovil.features.github.presentation.GithubScreen
import com.andressalvatierra.programovil.features.home.presentation.HomeScreen
import com.andressalvatierra.programovil.features.movie.presentation.PopularMoviesScreen
import com.andressalvatierra.programovil.features.profile.application.ProfileScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route // <-- Punto de partida es Home
    ) {
        // Nueva ruta para la pantalla Home
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        // Rutas existentes
        composable(route = Screen.Dollar.route) {
            DollarScreen()
        }
        composable(route = Screen.Github.route) {
            GithubScreen()
        }
        composable(route = Screen.PopularMovies.route) {
            PopularMoviesScreen()
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }
        composable(route = Screen.CardExamples.route) {
            CardScreen()
        }
    }
}