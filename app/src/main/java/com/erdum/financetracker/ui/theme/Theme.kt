package com.erdum.financetracker.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
  primary = Primary,
  background = Surface,
  surface = Surface,
  error = Destructive,
  onPrimary = TextPrimary,
  onSecondary = TextPrimary,
  onBackground = TextPrimary,
  onSurface = TextPrimary,
  onError = TextPrimary,


)

@Composable
fun FinanceTrackerTheme(
  content: @Composable () -> Unit
) {
  val colors = DarkColorPalette

  MaterialTheme(
    colorScheme = colors,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}
