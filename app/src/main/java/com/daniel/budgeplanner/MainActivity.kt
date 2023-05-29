package com.daniel.budgeplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.ui.theme.BudgeplannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudgeplannerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GetStarted()
                }
            }
        }
    }
}

@Preview
@Composable
fun GetStarted() {
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

            Shape()
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
            ContinueButton(text = "Comencemos") { }
        }
    }
}

@Composable
fun Shape() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.slice_3),
            contentDescription = "shape",
            modifier = Modifier
                .width(width = 218.dp)
                .height(height = 172.dp)
        )
    }
}

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
            elevation = ButtonDefaults.elevation(),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            colors = buttonColors(
                backgroundColor = Color(R.color.purple_700),
                contentColor = Color.Black

            ),
            modifier = Modifier
                .width(width = 325.dp)
                .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Bottom))
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