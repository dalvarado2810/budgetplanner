package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.R

@Composable
fun TopShape(title: String = "") {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "shape",
            modifier = Modifier
                .width(width = 218.dp)
                .height(height = 172.dp)
        )
        Text(
            text = title,
            color = Color.Black,
            textAlign = TextAlign.End,
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                lineBreak = LineBreak.Heading,
                letterSpacing = 2.sp
            ),
            modifier = Modifier
                .padding(
                    top = 80.dp,
                    end = 12.dp
                )
        )
    }
}