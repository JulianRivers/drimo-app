package com.drimo_app.views.start

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.drimo_app.R
import com.drimo_app.model.app.Routes
import com.drimo_app.viewmodels.inicio.SplashViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashView(navController: NavController, splashViewModel: SplashViewModel) {
    ContentSplashView(navController, splashViewModel)
}

@Composable
fun ContentSplashView(navController: NavController, splashViewModel: SplashViewModel) {
    val isLogged = splashViewModel.isLogged

    Box(modifier = Modifier.fillMaxSize()) {
        val alphaAnim = rememberInfiniteTransition(label = "Alpha Animation").animateFloat(
            initialValue = 0.3f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "Image Alpha Transition"
        )

        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaAnim.value)
        )
    }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(3000)
        navController.navigate(if (isLogged) Routes.Dreams.route else Routes.Login.route) {
            popUpTo("splash") { inclusive = true }
        }
    }

}


