package com.drimo_app.views.cycles

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.drimo_app.R
import com.drimo_app.components.MainTextField
import com.drimo_app.components.SpaceH
import com.drimo_app.ui.theme.White
import com.drimo_app.util.Constants.Companion.timeFormat
import com.drimo_app.viewmodels.cycles.CyclesViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CyclesView(navController: NavController, cyclesViewModel: CyclesViewModel) {
    Scaffold {
        ContentCyclesView(navController, cyclesViewModel)
    }
}

@Composable
fun ContentCyclesView(navController: NavController, cyclesViewModel: CyclesViewModel) {

    var currentTime by remember { mutableStateOf(Calendar.getInstance(TimeZone.getTimeZone("America/Bogota")).time) }

    // Actualiza la hora cada segundo
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota")).time
            delay(1000)
        }
    }

    // Formato de hora y fecha en español (América/Bogotá)
    val timeFormat = SimpleDateFormat("hh:mm a", Locale("es", "CO")).apply {
        timeZone = TimeZone.getTimeZone("America/Bogota")
    }
    val dateFormat = SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy", Locale("es", "CO")).apply {
        timeZone = TimeZone.getTimeZone("America/Bogota")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_2),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Ciclos",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.padding(top = 80.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = timeFormat.format(currentTime),
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = dateFormat.format(currentTime),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            InfoCard(
                title = "Dormir ahora",
                description = "Calcula los ciclos de sueño necesarios al acostarse en este momento",
                onClick = { cyclesViewModel.calculateCyclesNow(navController, currentTime) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            InfoCard(
                title = "¿A qué hora despertar?",
                description = "Calcula los ciclos de sueños necesarios según la hora que indiques que quieres dormir",
                onClick = { cyclesViewModel.askWakeUpTime(isWakeUpTime = false) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            InfoCard(
                title = "¿A qué hora dormir?",
                description = "Calcula los ciclos de sueño necesarios según la hora en la que indiques que te quieres levantar",
                onClick = { cyclesViewModel.askWakeUpTime(isWakeUpTime = true) }
            )
        }
    }
    if (cyclesViewModel.state.showAskSleepTime) {
        ModalAskSleepTime(
            onClick = {
                cyclesViewModel.saveSleepTime(navController, currentTime)
            },
            cyclesViewModel
        )
    }

    if (cyclesViewModel.state.showAskHourSleep) {
        ModalAskHourSleep(
            onClick = {
                cyclesViewModel.calculateCyclesSleep(navController, currentTime)
            },
            cyclesViewModel
        )
    }
}

@Composable
fun InfoCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2A5E)
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun ModalAskSleepTime(onClick: () -> Unit, cyclesViewModel: CyclesViewModel) {
    AlertDialog(
        onDismissRequest = {
            cyclesViewModel.closeModalAskSleepTime()
        },
        title = {
            Text(
                text = "Antes de comenzar",
                style = MaterialTheme.typography.bodyLarge,
                color = White,
            )
        },
        text = {
            Column {
                Text(
                    text = "Cuéntanos. ¿Cúanto tiempo te toma dormir? El tiempo que elijas se tomará como base para los cálculos en la aplicación.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = White,
                )
                SpaceH(10.dp)
                MainTextField(
                    value = cyclesViewModel.state.minutesSleepTime.toString(),
                    onValueChange = {
                        it.toIntOrNull()?.let { intValue ->
                            cyclesViewModel.onValue(intValue, "minutesSleepTime")
                        }
                    },
                    label = "Minutos",
                    keyboardType = KeyboardType.Number
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onClick
            ) {
                Text("Aceptar")
            }
        }
    )
}


@SuppressLint("UnrememberedMutableState")
@Composable
private fun ModalAskHourSleep(onClick: () -> Unit, cyclesViewModel: CyclesViewModel) {
    AlertDialog(
        onDismissRequest = {
            cyclesViewModel.closeModalAskHourSleep()
        },
        title = {
            Text(
                text =  if (cyclesViewModel.state.isWakeUpTime) {
                    "¿A qué horas te despertarás?"
                } else {
                    "¿A qué horas te irás a la cama?"
                },
                style = MaterialTheme.typography.bodyMedium,
                color = White,
            )
        },
        text = {
            Column {
                Text(
                    text = "La aplicación supondrá que tardarás hasta ${cyclesViewModel.state.minutesSleepTime} minutos en dormir para realizar los cálculos.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = White,
                )
                Text(
                    text = "Escribe la hora",
                    style = MaterialTheme.typography.bodyMedium,
                    color = White,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        MainTextField(
                            value = cyclesViewModel.state.hour.toString().padStart(2, '0'),
                            onValueChange = {
                                val sanitizedValue = it.takeLast(2)
                                sanitizedValue.toIntOrNull()?.let { intValue ->
                                    cyclesViewModel.onValue(intValue, "hour")
                                }
                            },
                            label = "",
                            modifier = Modifier.width(52.dp),
                            keyboardType = KeyboardType.Number
                        )
                        SpaceH()
                        Text(
                            text = "Hora",
                            style = MaterialTheme.typography.bodyMedium,
                            color = White
                        )
                    }
                    Text(
                        text = ":",
                        style = TextStyle(color = Color.White, fontSize = 40.sp),
                        color = White,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        MainTextField(
                            value = cyclesViewModel.state.minutes.toString().padStart(2, '0'),
                            onValueChange = {
                                val sanitizedValue = it.takeLast(2)
                                sanitizedValue.toIntOrNull()?.let { intValue ->
                                    cyclesViewModel.onValue(intValue, "minutes")
                                }
                            },
                            label = "",
                            modifier = Modifier.width(52.dp),
                            keyboardType = KeyboardType.Number
                        )
                        SpaceH()
                        Text(
                            text = "Min.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = White
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onClick
            ) {
                Text("Aceptar")
            }
        }
    )
}


