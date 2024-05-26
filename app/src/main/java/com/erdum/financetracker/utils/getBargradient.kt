
package com.erdum.financetracker.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun getBarGradient(): Brush {
    return Brush.verticalGradient(
        colors = listOf(
//
            Color(0xFF0095FF), // #0095FF
            Color(0xFF005999), // #005999
        )
    )
}
