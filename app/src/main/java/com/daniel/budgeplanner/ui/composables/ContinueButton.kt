package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.ui.theme.BudgetGreen

@Composable
fun ContinueButton(text: String, onButtonClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(width = 325.dp)
            .height(height = 60.dp)
    ) {
        Button(
            onClick = onButtonClick,
            elevation = ButtonDefaults.elevation(
                defaultElevation = 2.dp
            ),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BudgetGreen,
                contentColor = Color.Black

            ),
            modifier = Modifier
                .width(width = 325.dp)
                .height(height = 60.dp),
            contentPadding = PaddingValues(
                start = 32.dp,
                end = 32.dp
            )
        ) {
            Text(
                text = text,
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 19.sp
                )
            )
        }
    }
}