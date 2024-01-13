package com.daniel.budgetplanner.ui.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DialogText(
    text: String,
    size: Int = 18,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Text(
        text = text,
        color = Color.Black,
        fontSize = size.sp,
        fontWeight = fontWeight
    )
}