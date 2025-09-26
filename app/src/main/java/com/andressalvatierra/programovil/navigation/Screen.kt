package com.andressalvatierra.programovil.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Dollar : Screen("dollar_screen")
    object Github : Screen("github_screen")
    object PopularMovies : Screen("popular_movies_screen")
    object Profile : Screen("profile_screen")
    object CardExamples : Screen("card_examples_screen")
}