package com.drimo_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.drimo_app.navigation.NavManager
import com.drimo_app.ui.theme.DrimoAppTheme
import com.drimo_app.viewmodels.dreams.AddDreamViewModel
import com.drimo_app.viewmodels.inicio.LoginViewModel
import com.drimo_app.viewmodels.inicio.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrimoAppTheme () {
                NavManager()
            }
        }
    }
}
