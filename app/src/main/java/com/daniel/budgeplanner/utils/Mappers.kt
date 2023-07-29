package com.daniel.budgeplanner.utils

import com.daniel.budgeplanner.data.Category
import com.daniel.budgeplanner.data.MovementItem
import com.daniel.budgeplanner.domain.entity.Movement
import java.text.NumberFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

private const val END_DAY = 23
private const val ONE = 1
private const val MONTHS_OF_YEAR = 12

fun Movement.toMovementItem() = MovementItem (
    name = movementDescription,
    amount = movementAmount.toString(),
    category = movementCategory,
    date = obtainDateFormatted())

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
    val currentMonth = if (currentDate.dayOfMonth <= END_DAY) {
        currentDate.month.value - ONE
    } else { LocalDate.now().month.value }
    val currentYear = currentDate.year
    val nextYear = if (currentMonth == MONTHS_OF_YEAR) currentYear + ONE else currentYear

    val startDate = "$currentYear-0$currentMonth-24"
    val endDate = "${nextYear}-0${currentMonth+1}-23"
    return Pair(startDate, endDate)
}

fun Int.toNumberFormat(): String{
    val format = NumberFormat.getInstance()
    return format.format(this)
}

fun convertMillisToDate(millis: Long):LocalDate {
    val localDate = LocalDateTime
        .ofInstant(
            Instant.ofEpochMilli(millis),
            TimeZone.getDefault().toZoneId())
    return localDate.plusDays(1L).toLocalDate()
}

fun LocalDate.toViewPattern(): String = format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))