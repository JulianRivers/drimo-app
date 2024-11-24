package com.drimo_app.views.dreams

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.drimo_app.components.MessageDialog
import com.drimo_app.components.SpaceH
import com.drimo_app.model.dreams.UpdateDreamState
import com.drimo_app.viewmodels.dreams.UpdateDreamViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditDreamView(navController: NavController, dreamId: Int, updateDreamViewModel: UpdateDreamViewModel) {

    LaunchedEffect(dreamId) {
        updateDreamViewModel.loadDream(dreamId)
    }

    Scaffold() {
        ContentEditDreamView(navController, updateDreamViewModel)
    }
}

@Composable
fun ContentEditDreamView(navController: NavController, updateDreamViewModel: UpdateDreamViewModel) {
    val state = updateDreamViewModel.state
    val tagInput = remember { mutableStateOf("") }

    // Observa el estado del ViewModel para mostrar el mensaje de éxito o error
    val showDialog = updateDreamViewModel.showDialog
    val isSuccess = updateDreamViewModel.isSuccess
    val message = updateDreamViewModel.message

    Box(modifier = Modifier.fillMaxSize()) {
        // Fondo
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
            // Ícono de cerrar sesión
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = "Cerrar sesión",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable {
                            updateDreamViewModel.logout()
                            navController.navigate("login")
                        }
                        .size(32.dp)
                )
            }

            SpaceH(size = 120.dp)
            Text(text = "Edita tu sueño", style = MaterialTheme.typography.headlineMedium)
            SpaceH(size = 50.dp)

            // Campos de texto para edición
            MainTextField(
                value = state.title,
                onValueChange = { updateDreamViewModel.onValue(it, "title") },
                label = "Titulo",
                keyboardType = KeyboardType.Text
            )
            SpaceH(size = 15.dp)
            MainTextField(
                value = state.description,
                onValueChange = { updateDreamViewModel.onValue(it, "description") },
                label = "Descripción",
                keyboardType = KeyboardType.Text,
                height = 160.dp,
                singleLine = false
            )
            SpaceH(size = 15.dp)

            // Sección de etiquetas
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MainTextField(
                    value = tagInput.value,
                    onValueChange = { tagInput.value = it },
                    label = "Etiquetas máximo 3",
                    keyboardType = KeyboardType.Text,
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .height(56.dp)
                        .width(250.dp)
                )
                MainButton(
                    text = "+",
                    onClick = {
                        if (tagInput.value.isNotBlank() && state.tags.size < 3) {
                            val updatedTags = state.tags + tagInput.value.replaceFirstChar { it.uppercaseChar() }
                            updateDreamViewModel.onValue(updatedTags, "tags")
                            tagInput.value = ""
                        }
                    },
                    modifierButton = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
            }
            SpaceH(size = 10.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                state.tags.forEach { tag ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(110.dp)
                            .height(45.dp)
                            .padding(horizontal = 6.dp)
                            .background(Color(0xFF1F265E), shape = RoundedCornerShape(12.dp))
                            .padding(horizontal = 5.dp)
                            .clickable {
                                val updatedTags = state.tags - tag
                                updateDreamViewModel.onValue(updatedTags, "tags")
                            }
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

            SpaceH(size = 15.dp)

            // Factores de sueño
            Text(text = "Cuéntame más de tu sueño", style = MaterialTheme.typography.headlineSmall)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val factorLabels = listOf("Lúcido", "Pesadilla", "Sueño recurrente")
                factorLabels.forEach { factor ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = state.sleepFactors.contains(factor),
                            onCheckedChange = { isChecked ->
                                val updatedFactors = if (isChecked) {
                                    state.sleepFactors + factor
                                } else {
                                    state.sleepFactors - factor
                                }
                                updateDreamViewModel.onValue(updatedFactors, "sleepFactors")
                            }
                        )
                        Text(text = factor)
                    }
                }
            }

            SpaceH(size = 15.dp)

            // Botones de edición y eliminación
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                MainButton(
                    text = "Editar sueño",
                    onClick = {
                        updateDreamViewModel.updateDream()
                    },
                    modifierButton = Modifier
                        .weight(0.9f)
                        .padding(horizontal = 12.dp)
                        .height(50.dp)
                )
                MainButton(
                    text = "Eliminar sueño",
                    onClick = {
                        updateDreamViewModel.deleteDream()
                    },
                    modifierButton = Modifier
                        .weight(0.9f)
                        .padding(horizontal = 12.dp)
                        .height(50.dp)
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
                updateDreamViewModel.showDialog = false
                if (isSuccess && message == "Sueño eliminado exitosamente") {
                    navController.popBackStack()
                }
            }
        )
    }
}
