package com.drimo_app.views.cycles

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.drimo_app.R
import com.drimo_app.viewmodels.cycles.CyclesViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

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
                onClick = {  }
            )

            Spacer(modifier = Modifier.height(12.dp))

            InfoCard(
                title = "¿A qué hora despertar?",
                description = "Calcula los ciclos de sueños necesarios según la hora que indiques que quieres dormir",
                onClick = { /* No hay navegación en este momento */ }
            )

            Spacer(modifier = Modifier.height(12.dp))

            InfoCard(
                title = "¿A qué hora dormir?",
                description = "Calcula los ciclos de sueño necesarios según la hora en la que indiques que te quieres levantar",
                onClick = { /* No hay navegación en este momento */ }
            )
        }
    }
}

@Composable
fun InfoCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
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
