package com.example.practiceapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun navigate() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login_page") {
        composable(route = "login_page") { LoginScreen() }
        composable(route = "main_1") { FirstScreen(navController = navController) }
        composable(route = "main_2") { SecondScreen() }
    }
}



