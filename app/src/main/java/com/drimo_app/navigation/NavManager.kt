package com.drimo_app.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.drimo_app.model.app.Routes
import com.drimo_app.model.dreams.Dream
import com.drimo_app.viewmodels.dreams.AddDreamViewModel
import com.drimo_app.viewmodels.inicio.LoginViewModel
import com.drimo_app.viewmodels.inicio.RegisterViewModel
import com.drimo_app.views.dreams.AddDreamView
import com.drimo_app.views.dreams.DreamView
import com.drimo_app.views.dreams.EditDreamView
import com.drimo_app.views.start.LoginView
import com.drimo_app.views.start.RegisterView

@Composable
fun NavManager() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(route = Routes.Login.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginView(navController, loginViewModel)
        }

        composable(route = Routes.Register.route) {
            val registerViewModel: RegisterViewModel = hiltViewModel()
            RegisterView(navController, registerViewModel)
        }

        composable(route = Routes.AddDream.route) {
            val addDreamViewModel: AddDreamViewModel = hiltViewModel()
            AddDreamView(navController, addDreamViewModel)
        }

        composable(route = Routes.EditDream.route) {
            EditDreamView(navController)
        }

        composable(route = Routes.Dreams.route) {
            val dreams = listOf(
                Dream("Gibson se muere", "Íbamos a la casa de Gibson y Gibson se fue a pelear...", "Sáb", 28),
                Dream("Gibson se muere", "Íbamos a la casa de Gibson y Gibson se fue a pelear...", "Dom", 29)
            )
            DreamView(navController = navController, dreams = dreams)
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