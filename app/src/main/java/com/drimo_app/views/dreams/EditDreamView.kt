package com.drimo_app.views.dreams

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.drimo_app.R
import com.drimo_app.components.MainButton
import com.drimo_app.components.MainTextField
import com.drimo_app.components.SpaceH

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditDreamView(navController: NavController) {
    Scaffold() {
        ContentEditDreamView(navController)
    }
}

@Composable
fun ContentEditDreamView(navController: NavController) {
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
            Text(text = "Edita tu sueño", style = MaterialTheme.typography.headlineMedium)
            SpaceH(size = 50.dp)
            MainTextField(
                value = "Me cai de un avión",
                onValueChange = {  },
                label = "Titulo",
                keyboardType = KeyboardType.Text
            )
            SpaceH(size = 15.dp)
            MainTextField(
                value = "Estaba en un avión camino a la casa de julian cuando la cola del avión explota y me caigo",
                onValueChange = { },
                label = "Descripción",
                keyboardType = KeyboardType.Text,
                height = 160.dp,
                singleLine = false
            )
            SpaceH(size = 15.dp)
            // Tags Section
            val tagInput = remember { mutableStateOf("") }
            val tags = remember { mutableStateListOf<String>() }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MainTextField(
                    value = tagInput.value,
                    onValueChange = { tagInput.value = it },
                    label = "Etiquetas maximo 3",
                    keyboardType = KeyboardType.Text,
                )
                MainButton(
                    text = "+",
                    onClick = { },
                    modifierButton = Modifier.height(50.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                tags.forEach { tag ->
                    Text(
                        text = tag,
                        color = Color.White,
                        modifier = Modifier.wrapContentSize()
                    )
                }
            }
            SpaceH(size = 30.dp)


            // Sleep Factors Section
            Text(text = "Cuéntame más de tu sueño", style = MaterialTheme.typography.headlineSmall)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val sleepFactors = remember { mutableStateListOf(false, false, false) }
                val factorLabels = listOf("Lúcido", "Pesadilla", "Sueño recurrente")

                factorLabels.forEachIndexed { index, label ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = sleepFactors[index],
                            onCheckedChange = { }
                        )
                        Text(text = label)
                    }
                }
            }
            SpaceH(size = 15.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                MainButton(
                    text = "Editar sueño",
                    onClick = { },
                    modifierButton = Modifier
                        .weight(0.9f)
                        .padding(horizontal = 12.dp)
                        .height(50.dp)
                )
                MainButton(
                    text = "Eliminar sueño",
                    onClick = { },
                    modifierButton = Modifier
                        .weight(0.9f)
                        .padding(horizontal = 12.dp)
                        .height(50.dp)
                )
            }
        }
    }
}