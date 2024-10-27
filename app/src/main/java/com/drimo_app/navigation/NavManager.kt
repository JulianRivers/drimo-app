package com.drimo_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.drimo_app.model.app.Routes
import com.drimo_app.viewmodels.inicio.LoginViewModel
import com.drimo_app.viewmodels.inicio.RegisterViewModel
import com.drimo_app.views.start.LoginView
import com.drimo_app.views.start.RegisterView

@Composable
fun NavManager(loginViewModel: LoginViewModel, registerViewModel: RegisterViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(route = Routes.Login.route) {
            LoginView(navController, loginViewModel)
        }
        
        composable(route = Routes.Register.route) {
            RegisterView(navController, registerViewModel)
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