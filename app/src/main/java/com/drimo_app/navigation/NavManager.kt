package com.drimo_app.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.drimo_app.model.app.Routes
import com.drimo_app.model.dreams.Dream
import com.drimo_app.model.patterns.Factor
import com.drimo_app.model.patterns.SleepPattern
import com.drimo_app.viewmodels.dreams.AddDreamViewModel
import com.drimo_app.viewmodels.inicio.LoginViewModel
import com.drimo_app.viewmodels.inicio.RegisterViewModel
import com.drimo_app.views.cycles.CyclesView
import com.drimo_app.views.dreams.AddDreamView
import com.drimo_app.views.dreams.DreamView
import com.drimo_app.views.dreams.EditDreamView
import com.drimo_app.views.patterns.PatternsView
import com.drimo_app.views.start.LoginView
import com.drimo_app.views.start.RegisterView

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavManager(navController: NavHostController) {

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
            DreamView(navController = navController)
        }

        composable(route = Routes.Patterns.route) {
            val sleepPattern = SleepPattern(
                factors = listOf(
                    Factor("Recurrente", 4),
                    Factor("Lucidez", 3),
                    Factor("Pesadilla", 1)
                ),
                tags = listOf("Viaje", "Peligro", "Cancion"),
                completedCycles = 24,
                plannedHours = 96
            )
            PatternsView(sleepPattern)
        }

        composable(route = Routes.Cycles.route) {
            CyclesView(navController = navController)
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