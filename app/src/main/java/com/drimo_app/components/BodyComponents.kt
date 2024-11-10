package com.drimo_app.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SpaceH(size: Dp = 5.dp){
    Spacer(modifier = Modifier.height(size))
}

@Composable
fun SpaceW(size: Dp = 5.dp){
    Spacer(modifier = Modifier.width(size))
}

@Composable
fun MessageDialog(
    showDialog: MutableState<Boolean>,
    isSuccess: Boolean,
    message: String,
    onDismiss: () -> Unit
) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
                onDismiss()
            },
            title = {
                Text(
                    text = if (isSuccess) "¡Éxito!" else "Error",
                    color = if (isSuccess) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                )
            },
            text = { Text(message) },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog.value = false
                        onDismiss()
                    }
                ) {
                    Text("Cerrar")
                }
            }
        )
    }
}