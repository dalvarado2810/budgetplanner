package com.daniel.budgeplanner.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.ui.composables.ContinueButton
import com.daniel.budgeplanner.ui.composables.TopShape
import com.daniel.budgeplanner.utils.ScreensNavigation


@Composable
fun GetStarted(navController: NavController) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xfff0f4f3))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            TopShape()
            Text(
                text = "Tu Presupuesto",
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(id = R.drawable.true_friends),
                contentDescription = "image 1",
                modifier = Modifier
                    .width(width = 290.dp)
                    .height(height = 235.dp)
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp
                    )
            )

            Text(
                text = "Ajusta tus gastos mensuales",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 22.sp
                )
            )

            Text(
                text = "Te ayudamos a planificar tus gastos mensuales",
                color = Color.Black.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                ), modifier = Modifier
                    .width(160.dp)
                    .padding(
                        top = 6.dp,
                        bottom = 120.dp
                    )
            )
            ContinueButton(text = "Comencemos") {
                navController.navigate(ScreensNavigation.OnBoarding.routes)
            }
        }
    }
}