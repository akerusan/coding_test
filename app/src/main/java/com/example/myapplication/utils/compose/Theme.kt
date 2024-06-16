package com.example.myapplication.utils.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = ThemeColors.Night.primary,
    secondary = ThemeColors.Night.secondary,
    background = ThemeColors.Night.bgColor
)

private val LightColorPalette = lightColors(
    primary = ThemeColors.Day.primary,
    secondary = ThemeColors.Day.secondary,
    background = ThemeColors.Day.bgColor
)

@Composable
fun MyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content,
        colors = if (darkTheme) DarkColorPalette else LightColorPalette
    )
}