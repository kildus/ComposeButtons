package com.example.test1.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MyLightColorPalette = lightColors(
    primary = primaryColor,
//    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color.Green,
    surface = badgeForegroundColor,
    onPrimary = Color.White,
//    onSecondary = Color.Blue,
//    onBackground = Color.Gray,
    onSurface = Color.Black,
)


private val PressedColorPalette = lightColors(
    primary = primarySeparatorColor,
//    primaryVariant = Purple700,
//    secondary = Teal200,
//    background = Color.Magenta,
    surface = secondarySeparatorColor,
    onPrimary = badgeForegroundColor,
//    onSecondary = Color.Magenta,
//    onBackground = Color.Magenta,
//    onSurface = Color.Magenta,
)


private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    pressed: Boolean = false,
    content: @Composable () -> Unit
) {
//    val colors = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }


    val colors = if (pressed) {
        PressedColorPalette
    } else {
        MyLightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun PressedTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeNumber: Int = 2,
    content: @Composable () -> Unit
) {

//    val colors = PressedColorPalette
    val colors = when (themeNumber) {
        2 -> PressedColorPalette
        else -> MyLightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}