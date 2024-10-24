package com.example.botoneapp.views.inicio

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.drimo_app.R
import com.drimo_app.components.MainButton
import com.drimo_app.components.MainTextField
import com.drimo_app.components.SpaceH
import com.drimo_app.viewmodels.inicio.LoginViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginView(navController: NavController, loginViewModel: LoginViewModel) {
    Scaffold() {
        ContentLoginView(loginViewModel)
    }
}

@Composable
fun ContentLoginView(loginViewModel: LoginViewModel) {
    val state = loginViewModel.state

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Bienvenido a Drimo", style = MaterialTheme.typography.headlineMedium)
            SpaceH(size = 100.dp)
            MainTextField(
                value = state.correo,
                onValueChange = { loginViewModel.onValue(it, "correo") },
                label = "Correo electronico",
                keyboardType = KeyboardType.Email
            )
            SpaceH()
            MainTextField(
                value = state.password,
                onValueChange = { loginViewModel.onValue(it, "password") },
                label = "Contraseña",
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(),
            )
            SpaceH(size = 15.dp)
            MainButton(
                text = "Comencemos",
                onClick = { loginViewModel.iniciarSesion() },
                modifierButton = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                modifierText = Modifier.padding(vertical = 15.dp)
            )
            SpaceH(size = 10.dp)
            Text(
                text = "Ingresar como invitado",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge
            )
            SpaceH(size = 200.dp)
            Row {
                Text(
                    text = "¿No tienes una cuentas? ",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Regístrate!",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}