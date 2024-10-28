package com.drimo_app.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MainTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String, keyboardType: KeyboardType = KeyboardType.Text,
    maxLength: Int = 255,
    validator: ((String) -> String?)? = null,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation. None,
    height: Dp = 56.dp,
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxLength) {
                onValueChange(it)
            }
        },
        label = { Text(text = label, style = MaterialTheme.typography.bodyLarge) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .height(height),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.background,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.onPrimary,

            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,

            disabledContainerColor = MaterialTheme.colorScheme.onPrimary,

            errorContainerColor = MaterialTheme.colorScheme.onPrimary,
            errorTextColor = MaterialTheme.colorScheme.background,
            errorBorderColor = MaterialTheme.colorScheme.error,

            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.White,
        ),
        isError = validator != null,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
    )

}