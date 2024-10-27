package com.drimo_app.views.dreams

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.drimo_app.R
import com.drimo_app.components.MainTextField
import com.drimo_app.components.SpaceH
import com.drimo_app.viewmodels.dreams.AddDreamViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddDreamView(navController: NavController, addDreamViewModel: AddDreamViewModel) {
    Scaffold() {
        ContentAddDreamView(navController, addDreamViewModel)
    }
}

@Composable
fun ContentAddDreamView(navController: NavController, addDreamViewModel: AddDreamViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_2),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpaceH(size = 120.dp)
            Text(text = "Registra tus sueños", style = MaterialTheme.typography.headlineMedium)
            SpaceH(size = 50.dp)
            MainTextField(
                value = "",
                onValueChange = {  },
                label = "Titulo",
                keyboardType = KeyboardType.Text
            )
            SpaceH()
            MainTextField(
                value = "",
                onValueChange = {  },
                label = "Descripción",
                keyboardType = KeyboardType.Text,
            )
        }
    }
}