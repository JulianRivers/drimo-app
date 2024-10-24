package com.drimo_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.drimo_app.viewmodels.inicio.LoginViewModel
import com.example.botoneapp.views.inicio.LoginView

@Composable
fun NavManager(loginViewModel: LoginViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Login") {
        composable(route = "Login") {
            LoginView(navController, loginViewModel)
        }

        //ejemplo de navegación con deep links
//        composable(route = "Detail/{id}/?{opcional}", arguments = listOf(
//            navArgument("id"){type  = NavType.IntType},
//            navArgument("opcional"){type  = NavType.StringType}
//
//        )) {
//            val id = it.arguments?.getInt("id") ?: 0
//            val opcional = it.arguments?.getString("opcional")
//            //DetailView(navController, id, opcional)
//        }
    }
}