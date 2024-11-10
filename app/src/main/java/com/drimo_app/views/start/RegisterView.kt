package com.drimo_app.views.start

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
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
import com.drimo_app.components.MessageDialog
import com.drimo_app.components.SpaceH
import com.drimo_app.model.app.Routes
import com.drimo_app.viewmodels.inicio.RegisterViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterView(navController: NavController, registerViewModel: RegisterViewModel) {
    Scaffold() {
        ContentRegisterView(navController, registerViewModel)
    }
}
@Composable
fun ContentRegisterView(navController: NavController, registerViewModel: RegisterViewModel) {
    val state = registerViewModel.state

    // Observa el estado del ViewModel para mostrar el mensaje de éxito o error
    val showDialog = registerViewModel.showDialog
    val isSuccess = registerViewModel.isSuccess
    val message = registerViewModel.message

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
                value = state.email,
                onValueChange = { registerViewModel.onValue(it, "email") },
                label = "Correo electrónico",
                keyboardType = KeyboardType.Email
            )
            SpaceH()
            MainTextField(
                value = state.password,
                onValueChange = { registerViewModel.onValue(it, "password") },
                label = "Contraseña",
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(),
            )
            SpaceH()
            MainTextField(
                value = state.confirmPassword,
                onValueChange = { registerViewModel.onValue(it, "confirmPassword") },
                label = "Confirmar contraseña",
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(),
            )
            SpaceH(size = 15.dp)
            MainButton(
                text = "Crear cuenta",
                onClick = { registerViewModel.registerAccount(navController) },
                modifierButton = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                modifierText = Modifier.padding(vertical = 15.dp)
            )
            SpaceH(size = 200.dp)
            Row (modifier = Modifier.clickable {  navController.navigate(Routes.Login.route) }){
                Text(
                    text = "Iniciar sesión",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
    if (showDialog) {
        MessageDialog(
            showDialog = mutableStateOf(showDialog),
            isSuccess = isSuccess,
            message = message,
            onDismiss = {
                registerViewModel.showDialog = false
                // Navegar a la pantalla de login después de cerrar el modal
                if (isSuccess) {
                    navController.navigate(Routes.Login.route)
                }
            }
        )
    }
}