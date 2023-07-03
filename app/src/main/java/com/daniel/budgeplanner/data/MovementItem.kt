package com.daniel.budgeplanner.data


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
