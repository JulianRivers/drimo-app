package com.drimo_app.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.drimo_app.model.app.Routes
import com.drimo_app.viewmodels.blog.BlogViewModel
import com.drimo_app.viewmodels.cycles.CyclesViewModel
import com.drimo_app.viewmodels.dreams.AddDreamViewModel
import com.drimo_app.viewmodels.dreams.UpdateDreamViewModel
import com.drimo_app.viewmodels.inicio.LoginViewModel
import com.drimo_app.viewmodels.inicio.RegisterViewModel
import com.drimo_app.viewmodels.inicio.SplashViewModel
import com.drimo_app.viewmodels.patterns.PatternsViewModel
import com.drimo_app.views.cycles.CyclesResultView
import com.drimo_app.views.cycles.CyclesView
import com.drimo_app.views.dreams.AddDreamView
import com.drimo_app.views.dreams.DreamView
import com.drimo_app.views.dreams.EditDreamView
import com.drimo_app.views.patterns.PatternsView
import com.drimo_app.views.start.LoginView
import com.drimo_app.views.start.RegisterView
import com.drimo_app.views.start.SplashView
import com.drimo_app.views.blog.BlogView
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavManager(navController: NavHostController) {
    val cyclesViewModel: CyclesViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Routes.Splash.route) {

        composable(route = Routes.Splash.route) {
            val splashViewModel: SplashViewModel = hiltViewModel()
            SplashView(navController, splashViewModel)
        }

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

        composable(
            route = Routes.EditDream.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0

            val updateDreamViewModel: UpdateDreamViewModel = hiltViewModel()

            EditDreamView(
                navController = navController,
                dreamId = id,
                updateDreamViewModel = updateDreamViewModel
            )
        }

        composable(route = Routes.Dreams.route) {
            DreamView(navController = navController)
        }

        composable(route = Routes.Patterns.route) {
            val patternsViewModel: PatternsViewModel = hiltViewModel()
            PatternsView(patternsViewModel, navController)
        }

        composable(route = Routes.Cycles.route) {
            CyclesView(navController, cyclesViewModel)
        }

        composable(route = Routes.CyclesResult.route) {
            CyclesResultView(navController, cyclesViewModel)
        }

        composable(route = Routes.Blog.route) {
            val blogViewModel: BlogViewModel = hiltViewModel()
            BlogView(navController, blogViewModel)
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