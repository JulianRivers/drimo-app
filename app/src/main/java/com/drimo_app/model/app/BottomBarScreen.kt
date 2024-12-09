package com.drimo_app.model.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String = "",
    val icon: ImageVector,
    val isButton: Boolean = false
) {
    object Home : BottomBarScreen(
        route = Routes.Dreams.route,
        title = "Sueños",
        icon = Icons.Outlined.Home
    )

    object Cycles : BottomBarScreen(
        route = Routes.Cycles.route,
        title = "Ciclos",
        icon = Icons.Outlined.DateRange
    )

    object AddDream : BottomBarScreen(
        route = Routes.AddDream.route,
        icon = Icons.Default.Add,
        isButton = true
    )

    object Patterns : BottomBarScreen(
        route = Routes.Patterns.route,
        title = "Patrones",
        icon = Icons.Default.Menu
    )

    object Blog : BottomBarScreen(
        route = Routes.Blog.route,
        title = "Blog",
        icon = Icons.Default.MailOutline
    )
}

