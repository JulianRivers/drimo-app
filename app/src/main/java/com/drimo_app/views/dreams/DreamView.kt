package com.drimo_app.views.dreams

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.drimo_app.R
import com.drimo_app.model.dreams.Dream

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DreamView(navController: NavController, dreams: List<Dream>) {
    Scaffold {
        ContentDreamView(dreams)
    }
}

@Composable
fun ContentDreamView(dreams: List<Dream>) {
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
            verticalArrangement = Arrangement.Top
        ) {
            // Ajustamos para que "Sueños" esté más centrado hacia la mitad
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp), // Ajusta esta distancia según sea necesario
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Sueños",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Ajustamos "Septiembre" alineado a la derecha
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "Septiembre",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Lista de sueños
            dreams.forEach { dream ->
                DreamCard(dream = dream)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun DreamCard(dream: Dream) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color(0xFF2A2A5E)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = dream.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = dream.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White,
                    maxLines = 2
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = dream.dayOfWeek,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Text(
                    text = dream.day.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
            }
        }
    }
}
