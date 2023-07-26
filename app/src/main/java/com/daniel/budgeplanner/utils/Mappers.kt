package com.daniel.budgeplanner.utils

import com.daniel.budgeplanner.data.Category
import com.daniel.budgeplanner.data.MovementItem
import com.daniel.budgeplanner.domain.entity.Movement
import java.text.NumberFormat
import java.time.LocalDate

fun Movement.toMovementItem() = MovementItem (
    name = movementDescription,
    amount = movementAmount.toString(),
    category = movementCategory,
    date = date)

fun String.toCategoryItem(): Category {
    return when (this) {
        FOOD_EXPENSES -> Category.FOOD_EXPENSES
        ANT_EXPENSES -> Category.ANT_EXPENSES
        SERVICES_EXPENSES -> Category.SERVICES_EXPENSES
        MONTHLY_INCOMES -> Category.MONTHLY_INCOMES
        else -> Category.OTHER_INCOMES
    }
}

fun getDate(): Pair<String,String>{
    val currentDate = LocalDate.now()
    val currentMonth = if (currentDate.dayOfMonth <= 23) {
        LocalDate.now().month.value - 1
    } else { LocalDate.now().month.value }
    val currentYear = LocalDate.now().year
    val startDate = "24/0$currentMonth/$currentYear"
    val nextYear = if (currentMonth == 12) currentYear+1 else currentYear
    val endDate = "25/0${currentMonth+1}/$nextYear"
    return Pair(startDate, endDate)
}

fun Int.toNumberFormat(): String{
    val format = NumberFormat.getInstance()
    return format.format(this)
}