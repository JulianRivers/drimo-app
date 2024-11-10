package com.drimo_app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.drimo_app.model.app.BottomBarScreen
import com.drimo_app.model.app.Routes
import com.drimo_app.navigation.NavManager

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar(currentRoute)) {
                BottomBar(navController = navController)
            }
        }
    ) {
        NavManager(navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Cycles,
        BottomBarScreen.AddDream,
        BottomBarScreen.Patterns,
        BottomBarScreen.Blog,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Color(0xFF0E1B5A)
    ) {
        screens.forEach { screen ->
            if (screen.route == Routes.AddDream.route) {
                // Botón central especial
                AddFloatingItem(
                    screen = screen,
                    navController = navController
                )
            } else {
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    NavigationBarItem(

        label = {
            Text(
                screen.title,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
            )
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
                tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route)
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
            unselectedIconColor = Color.White,
            indicatorColor = Color.Transparent
        ),
    )
}

@Composable
fun RowScope.AddFloatingItem(
    screen: BottomBarScreen,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .size(56.dp)
    ) {
        FloatingActionButton(
            onClick = { navController.navigate(screen.route) },
            containerColor = Color(0xFF8777FB),
            shape = RoundedCornerShape(16.dp),
            elevation = FloatingActionButtonDefaults.elevation(8.dp)
        ) {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Add Icon",
                tint = Color.White
            )
        }
    }
}

private fun shouldShowBottomBar(route: String?): Boolean {
    val routesWithoutBottomBar = listOf(Routes.Login.route, Routes.Register.route)
    return route != null && route !in routesWithoutBottomBar
}