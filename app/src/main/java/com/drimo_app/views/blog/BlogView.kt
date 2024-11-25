package com.drimo_app.views.blog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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

@Composable
fun ContentBlogView(navController: NavController, blogViewModel: BlogViewModel) {
    // Variable de estado única para la imagen seleccionada
    var selectedImage by remember { mutableStateOf<Int?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_2),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .verticalScroll(rememberScrollState()),
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
            ClickableImage(
                drawableRes = R.drawable.ciclos,
                onClick = { selectedImage = R.drawable.ciclos }
            )
            ClickableImage(
                drawableRes = R.drawable.mejorar,
                onClick = { selectedImage = R.drawable.mejorar }
            )
            SpaceH(size = 50.dp)
        }
    }

    // Mostrar el diálogo de zoom si hay una imagen seleccionada
    selectedImage?.let { imageRes ->
        ZoomImageDialog(
            imageRes = imageRes,
            onDismiss = { selectedImage = null }
        )
    }
}

@Composable
fun ClickableImage(@DrawableRes drawableRes: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = drawableRes),
        contentDescription = "Imagen clickeable",
        modifier = Modifier
            .size(400.dp)
            .clickable { onClick() }
    )
}

@Composable
fun ZoomImageDialog(@DrawableRes imageRes: Int, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        // Estado para controlar el zoom y la posición de la imagen
        var scale by remember { mutableStateOf(1f) }
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale = (scale * zoom).coerceIn(1f, 5f) // Limita el zoom entre 1x y 5x
                        offsetX += pan.x
                        offsetY += pan.y
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Imagen ampliada",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offsetX,
                        translationY = offsetY
                    )
                    .clickable { onDismiss() } // Cerrar el zoom al hacer clic
            )
        }
    }
}