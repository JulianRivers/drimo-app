package com.drimo_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.drimo_app.R

val Aleo = FontFamily(
    Font(R.font.aleo_regular, FontWeight.Normal),
    Font(R.font.aleo_bold, FontWeight.Bold),
    Font(R.font.aleo_thin, FontWeight.Thin),
    Font(R.font.aleo_black, FontWeight.Black),
    Font(R.font.aleo_light, FontWeight.Light),
    Font(R.font.aleo_medium, FontWeight.Medium),
    Font(R.font.aleo_italic, FontWeight.Normal, FontStyle.Italic),

)
// Set of Material typography styles to start with
val AppTypography = Typography(
    displayLarge = TextStyle( // h1
        fontFamily = Aleo,
        fontWeight = FontWeight.Light,
        fontSize = 85.sp,
        letterSpacing = (-1.5).sp
    ),
    displayMedium = TextStyle( // h2
        fontFamily = Aleo,
        fontWeight = FontWeight.Light,
        fontSize = 53.sp,
        letterSpacing = (-0.5).sp
    ),
    displaySmall = TextStyle( // h3
        fontFamily = Aleo,
        fontWeight = FontWeight.Normal,
        fontSize = 42.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle( // h4
        fontFamily = Aleo,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        letterSpacing = 0.25.sp
    ),
    headlineSmall = TextStyle( // h5
        fontFamily = Aleo,
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle( // h6
        fontFamily = Aleo,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        letterSpacing = 0.15.sp
    ),
    titleMedium = TextStyle( // subtitle1
        fontFamily = Aleo,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle( // subtitle2
        fontFamily = Aleo,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle( // body1
        fontFamily = Aleo,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle( // body2
        fontFamily = Aleo,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle( // caption
        fontFamily = Aleo,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle( // button
        fontFamily = Aleo,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = 1.25.sp
    ),
    labelSmall = TextStyle( // overline
        fontFamily = Aleo,
        fontWeight = FontWeight.Normal,
        fontSize = 9.sp,
        letterSpacing = 1.5.sp
    )
)