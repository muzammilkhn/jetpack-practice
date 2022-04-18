package com.example.practiceapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

val screens = listOf(
    DrawerScreens.FirstScreen,
    DrawerScreens.SecondScreen,
)

sealed class DrawerScreens(
        val route: String,
        val title: String,
        val screenToLoad: @Composable ( controller:NavController) -> Unit
    ) {
        object FirstScreen : DrawerScreens("home", "Home", {
            FirstScreen(it)
        })

        object SecondScreen : DrawerScreens("settings", "Settings", {
            SecondScreen()
        })


    }
