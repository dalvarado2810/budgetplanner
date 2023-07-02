package com.daniel.budgeplanner.utils

import com.daniel.budgeplanner.data.Category
import com.daniel.budgeplanner.data.MovementItem
import com.daniel.budgeplanner.domain.entity.Movement

fun Movement.toMovementItem() = MovementItem (
    name = movementDescription,
    amount = movementAmount,
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
