package com.daniel.budgetplanner.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.daniel.budgetplanner.R
import com.daniel.budgetplanner.ui.theme.BudgetGreen
import com.daniel.budgetplanner.ui.theme.ExpensesColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerView(datePickerState: DatePickerState) {

    DatePicker(
        state = datePickerState,
        modifier = Modifier.background(
            BudgetGreen,
            RoundedCornerShape(28.dp)
        ),
        title = {
            Text(
                modifier = Modifier
                    .padding(
                        start = 148.dp,
                        top = 25.dp
                    ),
                text = stringResource(id = R.string.select_date)
            )
        },
        colors = DatePickerDefaults.colors(
            headlineContentColor = Color.Black,
            yearContentColor = Color.Black,
            selectedDayContainerColor = ExpensesColor,
            todayContentColor = ExpensesColor,
            todayDateBorderColor = ExpensesColor,
            disabledYearContentColor = Color.Black,
            currentYearContentColor = Color.Black,
            selectedYearContentColor = Color.Black,
            disabledSelectedYearContentColor = Color.Black
        ),
        showModeToggle = true
    )
}