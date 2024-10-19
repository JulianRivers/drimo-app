package com.drimo_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.drimo_app.views.HomeView

@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(navController=navController, startDestination = "Home"){
        composable(route = "Home") {
            HomeView(navController)
        }
        composable(route = "Detail/{id}/?{opcional}", arguments = listOf(
            navArgument("id"){type  = NavType.IntType},
            navArgument("opcional"){type  = NavType.StringType}

        )) {
            val id = it.arguments?.getInt("id") ?: 0
            val opcional = it.arguments?.getString("opcional")
            //DetailView(navController, id, opcional)
        }
    }
}