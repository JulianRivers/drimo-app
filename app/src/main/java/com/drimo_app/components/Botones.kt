package com.drimo_app.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MainButton(
    text: String,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifierText: Modifier = Modifier,
    modifierButton: Modifier = Modifier

) {
    Button(
        onClick = onClick, enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        modifier = modifierButton
    ) {
        Text(text = text, style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center, modifier = modifierText)
    }
}