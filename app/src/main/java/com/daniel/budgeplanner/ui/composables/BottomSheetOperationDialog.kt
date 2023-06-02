package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.ui.theme.BudgetGreen

@Composable
fun BottomSheetOperationDialog(
    color: Color,
    saveClick: () ->  Unit
) {

    val title = if (color == BudgetGreen) "INGRESO" else "GASTO"
    val categories = if (color == BudgetGreen) {
        listOf("Ingreso Mensual", "Ingresos Varios")
    } else {
        listOf("Gasto de alimentacion", "Gastos hormiga", "Servicios")
    }

    var descriptionText by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var amountText by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier
            .padding(bottom = 12.dp)
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            color = color,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .padding(vertical = 8.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        }

        DialogText(
            text = "Descripción",
            size = 18
        )


        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = 2.dp,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            TextField(
                value = descriptionText,
                onValueChange = {
                    if (it.text.length <= 26) descriptionText = it },
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    cursorColor = Color.White,
                    backgroundColor = color
                ),
                modifier = Modifier
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            )
        }


        DialogText(
            text = "Monto",
            size = 18
        )

        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = 2.dp,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            TextField(
                value = amountText,
                onValueChange = {
                    if (it.text.length <= 26) amountText = it },
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    cursorColor = Color.White,
                    backgroundColor = color
                ),
                modifier = Modifier
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.dollar),
                        contentDescription = "Prefix"
                    )
                }
            )
        }

        DialogText(
            text = "Tipo",
            size = 18
        )

        Column(horizontalAlignment = Alignment.Start) {
            categories.forEach {
                RoundedCheckButton(item = it)
            }
        }


        ContinueButton(text = "Guardar", onButtonClick = saveClick)
    }
}




