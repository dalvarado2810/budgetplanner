package com.daniel.budgeplanner.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.data.Category
import com.daniel.budgeplanner.data.MovementItem
import com.daniel.budgeplanner.ui.composables.*
import com.daniel.budgeplanner.utils.ScreensNavigation

@Composable
fun MonthlyPlanner(
    navController: NavController){

    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xfff0f4f3))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TopShape("Planifiquemos tu presupuesto")

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {

                Text(
                    text = "A continuación agrega tu gasto o " +
                            "ingreso segun su clasificación y al " +
                            "terminar presiona continuar y tendrás " +
                            "la planificación de tu presupuesto mensual.",
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        lineBreak = LineBreak.Simple,
                        lineHeight = 24.sp
                    )
                )

                Row(
                    modifier = Modifier
                        .padding(
                            horizontal = 18.dp,
                            vertical = 16.dp
                        )
                ) {

                    MovementButton(
                        icon = R.drawable.income_icon,
                        text = "Ingreso"
                    ) {
                        navController.navigate(ScreensNavigation.GetStarted.routes)
                    }

                    Spacer(
                        modifier = Modifier
                            .width(32.dp)
                    )

                    MovementButton(
                        icon = R.drawable.outcome_icon,
                        text = "Gasto"
                    ) {
                        navController.navigate(ScreensNavigation.GetStarted.routes)
                    }
                }

                Text(
                    text = "Ultimas transacciones agregadas",
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                )

                val itemList = movementItemList()
                if (itemList.isEmpty()) {
                    NoMovementsItem()
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                    ) {
                        items(movementItemList()) { item ->
                            MovementItem(item = item)
                        }
                    }
                }
            }

        }

        Column(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 12.dp)
            .height(60.dp)) {
            ContinueButton(text = "Calcula ahora tu presupuesto") {
                navController.navigate(ScreensNavigation.OnBoarding.routes)
            }
        }
    }
}

fun movementItemList() = listOf(MovementItem(
    name = "Pago de Salario",
    category = Category.MONTHLY_INCOMES,
    date = "23/05/2023",
    amount = "200.000.000"
    ),
    MovementItem(
        name = "Esval",
        category = Category.SERVICES_EXPENSES,
        date = "28/05/2023",
        amount = "62.000"
    ),
    MovementItem(
        name = "Pago de Finiquito Vane",
        category = Category.OTHER_INCOMES,
        date = "20/05/2023",
        amount = "200.000"
    ),
    MovementItem(
        name = "GASCO",
        category = Category.SERVICES_EXPENSES,
        date = "18/05/2023",
        amount = "68.000"
    ),
    MovementItem(
        name = "Metamucil",
        category = Category.ANT_EXPENSES,
        date = "29/05/2023",
        amount = "28.000"
    ),
    MovementItem(
        name = "Carnes",
        category = Category.FOOD_EXPENSES,
        date = "28/05/2023",
        amount = "190.000"
    )
)
