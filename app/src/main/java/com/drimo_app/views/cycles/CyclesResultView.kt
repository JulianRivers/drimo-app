package com.drimo_app.views.cycles

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.drimo_app.viewmodels.cycles.CyclesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CyclesResultView(navController: NavController, cyclesViewModel: CyclesViewModel) {
    Scaffold {
        ContentCyclesResultView(navController, cyclesViewModel)
    }
}

@Composable
fun ContentCyclesResultView(navController: NavController, cyclesViewModel: CyclesViewModel) {

}
