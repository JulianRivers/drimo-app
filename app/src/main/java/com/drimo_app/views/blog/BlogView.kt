package com.drimo_app.views.blog

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.drimo_app.R
import com.drimo_app.components.SpaceH
import com.drimo_app.model.app.Routes
import com.drimo_app.viewmodels.blog.BlogViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BlogView(navController: NavController, blogViewModel: BlogViewModel) {
    Scaffold() {
        ContentBlogView(navController, blogViewModel)
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ContentBlogView(navController: NavController, blogViewModel: BlogViewModel) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_2),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 24.dp),
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
                            blogViewModel.logout()
                            navController.navigate(Routes.Login.route)
                        }
                        .size(32.dp)
                )
            }

            SpaceH(size = 50.dp)
            Text(text = "Blog informativo", style = MaterialTheme.typography.headlineMedium)
            SpaceH(size = 50.dp)
        }
    }
}