package com.drimo_app.views.dreams

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.drimo_app.R
import com.drimo_app.components.MainButton
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
    val state = addDreamViewModel.state

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
                value = state.title,
                onValueChange = { addDreamViewModel.onValue(it, "title") },
                label = "Titulo",
                keyboardType = KeyboardType.Text
            )
            SpaceH(size = 15.dp)
            MainTextField(
                value = state.description,
                onValueChange = { addDreamViewModel.onValue(it, "description") },
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
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MainTextField(
                    value = tagInput.value,
                    onValueChange = { tagInput.value = it },
                    label = "Etiquetas maximo 3",
                    keyboardType = KeyboardType.Text,
                    modifier = Modifier.padding(horizontal = 30.dp).height(56.dp).width(250.dp)
                )
                MainButton(
                    text = "+",
                    onClick = {
                        if (tagInput.value.isNotBlank() && tags.size < 3) {
                            tags.add(tagInput.value)
                            addDreamViewModel.onValue(tags.toList(), "tags")
                            tagInput.value = ""
                        }
                    },
                    modifierButton = Modifier.height(50.dp).width(50.dp),
                )
            }
            SpaceH(size = 10.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                tags.forEach { tag ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(110.dp)
                            .height(45.dp)
                            .padding(horizontal = 6.dp)
                            .background(Color(0xFF1F265E), shape = RoundedCornerShape(12.dp))
                            .padding(horizontal = 5.dp )
                    ) {
                        Text(
                            text = tag,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            SpaceH(size = 40.dp)


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
                            onCheckedChange = { isChecked ->
                                sleepFactors[index] = isChecked
                                addDreamViewModel.onValue(
                                    sleepFactors.mapIndexedNotNull { i, checked ->
                                        if (checked) factorLabels[i] else null
                                    },
                                    "sleepFactors"
                                )
                            }
                        )
                        Text(text = label)
                    }
                }
            }
            SpaceH(size = 15.dp)
            MainButton(
                text = "Guardar sueño",
                onClick = { addDreamViewModel.addDream() },
                modifierButton = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp).height(50.dp)
            )
        }
    }
}