package com.example.practiceapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun navigate() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "main1") {
        composable(route = "main1") { MainScreen1(navController = navController) }
        composable(route = "main2") { MainScreen2() }

    }
}



