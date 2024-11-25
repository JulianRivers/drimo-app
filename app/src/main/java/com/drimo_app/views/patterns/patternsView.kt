package com.drimo_app.views.patterns

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.drimo_app.R
import com.drimo_app.model.app.Routes
import com.drimo_app.model.patterns.SleepPattern
import com.drimo_app.viewmodels.patterns.PatternsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PatternsView(patternsViewModel: PatternsViewModel, navController: NavController) {

    LaunchedEffect(Unit) {
        patternsViewModel.loadStatistics()
    }
    Scaffold {
        ContentPatternsView(patternsViewModel, navController)
    }
}

@Composable
fun ContentPatternsView(patternsViewModel: PatternsViewModel, navController: NavController) {
    val sleepPattern = patternsViewModel.sleepPattern.value
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                            patternsViewModel.logout()
                            navController.navigate(Routes.Login.route)
                        }
                        .size(32.dp)
                )
            }
            Text(
                text = "Patrones de sueño",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.padding(top = 50.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(2.dp)
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Factores de sueño",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Factores de sueño organizados en filas
            sleepPattern.factors.forEach { factor ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = androidx.compose.material3.CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = factor.name,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF4A4A7D),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .size(36.dp),
                        colors = androidx.compose.material3.CardDefaults.cardColors(
                            containerColor = Color(0xFF4A4A7D)
                        )
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = factor.count.toString(),
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Etiquetas más usadas",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Centrado de etiquetas en filas
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                sleepPattern.tags.take(3).forEach { tag ->
                    TagItem(tag)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Estadísticas
            StatisticRow("Ciclos de sueño completados", sleepPattern.completedCycles.toString())
            StatisticRow("Horas de sueño planeadas", sleepPattern.plannedHours.toString())
        }
    }
}

@Composable
fun TagItem(tag: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .wrapContentWidth(), // Ajusta el ancho al contenido
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp) // Ajuste de padding
        ) {
            Text(
                text = tag,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF4A4A7D),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun StatisticRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            fontSize = 16.sp
        )

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(48.dp) // Ajuste de ancho fijo para centrar el valor
                .height(36.dp),
            colors = androidx.compose.material3.CardDefaults.cardColors(
                containerColor = Color(0xFF4A4A7D)
            )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}
