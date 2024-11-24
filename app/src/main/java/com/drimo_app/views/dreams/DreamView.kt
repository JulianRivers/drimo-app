package com.drimo_app.views.dreams

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.drimo_app.R
import com.drimo_app.model.app.Routes
import com.drimo_app.model.dreams.Dream
import com.drimo_app.viewmodels.dreams.DreamViewModel
import com.google.gson.Gson
import formatDreamDate
import groupDreamsByMonth

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DreamView(navController: NavController, dreamViewModel: DreamViewModel = hiltViewModel()) {
    val dreams = dreamViewModel.dreams.collectAsState(initial = emptyList())
    val groupedDreams = groupDreamsByMonth(dreams.value)

    ContentDreamView(
        groupedDreams = groupedDreams,
        onLogoutClick = {
            dreamViewModel.logout()
            navController.navigate(Routes.Login.route)
        }
    ) { dream ->
        navController.navigate(Routes.EditDream.createRoute(dream.id))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentDreamView(
    groupedDreams: Map<String, List<Dream>>,
    onLogoutClick: () -> Unit,
    onDreamClick: (Dream) -> Unit
) {
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
                        .clickable(onClick = onLogoutClick)
                        .size(32.dp)
                )
            }

            // Título
            Text(
                text = "Sueños",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 80.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de sueños
            LazyColumn {
                groupedDreams.forEach { (month, dreams) ->
                    item {
                        Text(
                            text = month.capitalize(),
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    items(dreams) { dream ->
                        DreamCard(dream = dream, onClick = { onDreamClick(dream) })
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DreamCard(dream: Dream, onClick: () -> Unit) {
    Card(
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
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
                val formattedDate = formatDreamDate(dream.dateDream)
                Text(
                    text = formattedDate.split(" ")[0], // Día de la semana
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Text(
                    text = formattedDate.split(" ")[1], // Día numérico
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
            }
        }
    }
}
