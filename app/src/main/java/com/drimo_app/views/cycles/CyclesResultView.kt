package com.drimo_app.views.cycles

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.drimo_app.R
import com.drimo_app.components.SpaceH
import com.drimo_app.ui.theme.LightBlue
import com.drimo_app.ui.theme.LightGrey
import com.drimo_app.ui.theme.White
import com.drimo_app.util.Constants.Companion.timeFormat
import com.drimo_app.viewmodels.cycles.CyclesViewModel
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CyclesResultView(navController: NavController, cyclesViewModel: CyclesViewModel) {
    Scaffold {
        ContentCyclesResultView(navController, cyclesViewModel)
    }
}

@Composable
fun ContentCyclesResultView(navController: NavController, cyclesViewModel: CyclesViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_3),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(end = 8.dp, top = 22.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = White

                    )
                }
                Text(
                    text = "Para descansar mejor",
                    style = MaterialTheme.typography.headlineSmall,
                    color = White,
                    modifier = Modifier.padding(top = 35.dp)
                )
            }
            Text(
                text = "Si te dormirás a las ${timeFormat.format(cyclesViewModel.state.hourCurrently)}",
                style = MaterialTheme.typography.headlineSmall,
                color = LightGrey,
                modifier = Modifier
                    .padding(top = 35.dp, start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Descansa",
                    style = MaterialTheme.typography.headlineSmall,
                    color = White,
                    modifier = Modifier.padding(top = 35.dp)
                )
                Text(
                    text = "Despierta a las",
                    style = MaterialTheme.typography.headlineSmall,
                    color = White,
                    modifier = Modifier.padding(top = 35.dp)
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .align(Alignment.CenterHorizontally),
                thickness = 4.dp,
                color = LightBlue,
            )
            SpaceH()
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(cyclesViewModel.state.sleepCycles.reversed()) { index, hour ->
                    CycleCard(
                        index = cyclesViewModel.state.sleepCycles.size - index,
                        hour = hour,
                        onClick = { }
                    )
                    SpaceH()
                }
            }
        }
    }
}


@Composable
private fun CycleCard(index: Int, hour: Date, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = LightBlue
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(
                    text = "$index Ciclos de sueño",
                    style = MaterialTheme.typography.titleLarge,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
                SpaceH()
                Text(
                    text = "Dormirás\naproximadamente",
                    style = MaterialTheme.typography.titleLarge,
                    color = LightGrey

                )
            }
            Text(
                text = timeFormat.format(hour),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }

    }
}

