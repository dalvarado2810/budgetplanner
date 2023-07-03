package com.daniel.budgeplanner.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.daniel.budgeplanner.MainContract
import com.daniel.budgeplanner.MainViewModel
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.ui.composables.ContinueButton
import com.daniel.budgeplanner.ui.composables.InputTextField
import com.daniel.budgeplanner.ui.composables.TopShape
import com.daniel.budgeplanner.utils.ScreensNavigation

@Composable
fun Onboarding(
    navController: NavController,
    viewModel: MainViewModel
) {

    var name by remember { mutableStateOf("") }

    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xfff0f4f3))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            TopShape("Configuremos tu presupuesto")

            InputTextField(title = "Danos tu nombre") {
                name = it
            }

            Text(
                text = "Tenemos gastos e ingresos predeterminados pero tambien podrás ingresar operaciones personailzadas para ti.",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    lineBreak = LineBreak.Paragraph,
                    lineHeight = 20.sp
                ),
                modifier = Modifier
                    .padding(
                        vertical = 8.dp,
                        horizontal = 12.dp
                    )
            )

            CategoryText(
                text = "Las categorias de gastos estan divididas en:")

            LeftRow(
                resourceId = R.drawable.shopping_cart,
                titleText = "Gastos de Alimentación",
                subTitleText = "Incluye aqui todos tus gastos de alimentación"
            )

            RightRow(
                resourceId = R.drawable.money,
                titleText = "Gastos hormiga",
                subTitleText = "Incluye aqui todos los gastos que no tengas establecidos dentro de tu presupuesto"
            )

            LeftRow(
                resourceId = R.drawable.services,
                titleText = "Servicios",
                subTitleText = "Incluye aquí todos los gastos de servicios públicos y privados"
            )

            CategoryText(text = "Las categorias de ingresos estan divididas en:")

            RightRow(
                resourceId = R.drawable.finance_icon,
                titleText = "Ingresos mensuales",
                subTitleText = "Incluye aquí todos los ingresos derivados de salarios o bonificaciones mensuales"
            )

            LeftRow(
                resourceId = R.drawable.various_input,
                titleText = "Ingresos varios",
                subTitleText = "Incluye aquí todos los ingresos adicionales que obtengas en el mes"
            )

            Text(
                text = "Ahora comencemos a planificar el presupuesto de tu mes.",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp
                ),
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp,
                        end = 12.dp,
                        start = 12.dp
                    )
            )

            ContinueButton(text = "Continuemos") {
                viewModel.setEvent(MainContract.Event.AddName(name))
                navController.navigate(ScreensNavigation.MonthlyPlanner.routes)
            }
        }
    }
}

@Composable
fun LeftRow(
    resourceId: Int,
    titleText: String,
    subTitleText: String
) {
    Row(
        modifier = Modifier
            .padding(
                top = 12.dp,
                end = 36.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = "icon",
            modifier = Modifier
                .padding()
                .height(50.dp)
                .width(50.dp))
        Column(modifier = Modifier
            .padding(
                start = 14.dp
            )) {
            Text(
                text = titleText,
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp),
            )
            Text(
                text = subTitleText,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 13.sp),
                modifier = Modifier
                    .width(250.dp))
        }
    }
}

@Composable
fun RightRow(
    resourceId: Int,
    titleText: String,
    subTitleText: String
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding( top = 12.dp)
    ) {
        Column (
            modifier = Modifier
                .padding( end = 8.dp)
                ) {
            Text(
                text = titleText,
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp),
            )
            Text(
                text = subTitleText,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 13.sp),
                modifier = Modifier
                    .width(290.dp))
        }
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = "icon",
            modifier = Modifier
                .height(50.dp)
                .width(50.dp))
    }
}

@Composable
fun CategoryText(
    text: String
) {
    Text(
        text = text,
        color = Color.Black,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineBreak = LineBreak.Heading,
            lineHeight = 20.sp
        ),
        modifier = Modifier
            .padding(
                top = 12.dp,
                bottom = 8.dp,
                end = 80.dp
            )
    )
}