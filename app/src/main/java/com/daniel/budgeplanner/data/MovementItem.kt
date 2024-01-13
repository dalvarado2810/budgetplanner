package com.daniel.budgeplanner.data

data class MovementItem(
    val name: String,
    val category: Category,
    val date: String,
    val amount: String
)

enum class Category(val gasto: String) {
    FOOD_EXPENSES("Alimentación"),
    ANT_EXPENSES("Gastos hormiga"),
    SERVICES_EXPENSES("Servicios Públicos"),
    HEALTH_EXPENSES("Salud"),
    OUTFIT_EXPENSES("Vestimenta"),
    TRANSPORTATION_EXPENSES("Transporte"),
    MONTHLY_INCOMES("Ingresos Mensuales"),
    OTHER_INCOMES("Ingresos varios")
}

enum class Steps(val step: Int){
    STEP_ONE(1),
    STEP_TWO(2),
    STEP_THREE(3)
}

data class DashboardBalances(
    val actualBalance: Int = 0,
    val monthlyIncomeBalance: Int = 0,
    val otherIncomesBalance: Int = 0,
    val foodExpensesBalance: Int = 0,
    val healthExpensesBalance: Int = 0,
    val servicesExpensesBalance: Int = 0,
    val transportExpensesBalance: Int = 0,
    val outfitExpensesBalance: Int = 0,
    val antExpensesBalance: Int = 0
)