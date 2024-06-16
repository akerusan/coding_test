package com.example.myapplication.utils.compose

import androidx.compose.ui.graphics.Color

// light theme
val LightPrimary = Color(0xFFD0BCFF)
val LightSecondary = Color(0xFFCCC2DC)
val LightBgColor = Color(0xFFEFEFEF)

// dark theme
val DarkPrimary = Color(0xFF6650a4)
val DarkSecondary = Color(0xFF625b71)
val DarkBgColor = Color(0xFF2C2C2C)

sealed class ThemeColors(
    val primary: Color,
    val secondary: Color,
    val bgColor: Color
)  {
    data object Night: ThemeColors(
        primary = DarkPrimary,
        secondary = DarkSecondary,
        bgColor = DarkBgColor
    )
    data object Day: ThemeColors(
        primary = LightPrimary,
        secondary = LightSecondary,
        bgColor = LightBgColor
    )
}