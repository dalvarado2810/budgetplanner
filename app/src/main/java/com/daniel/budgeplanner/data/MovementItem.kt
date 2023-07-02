package com.daniel.budgeplanner.data

import androidx.compose.ui.graphics.Color

data class MovementItem(
    val name: String,
    val category: Category,
    val date: String,
    val amount: String
)

enum class Category(val gasto: String) {
    FOOD_EXPENSES("Gastos de Alimentación"),
    ANT_EXPENSES("Gastos hormiga"),
    SERVICES_EXPENSES("Servicios Públicos"),
    MONTHLY_INCOMES("Ingresos Mensuales"),
    OTHER_INCOMES("Ingresos varios")
}

data class CheckButton(
    val category: Category,
    val checked: Boolean,
    val color: Color
)
