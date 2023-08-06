package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerDefaults
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.ui.theme.BudgetGreen
import com.daniel.budgeplanner.ui.theme.ExpensesColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDateRangePicker(
    state: DateRangePickerState
){
    DateRangePicker(
        state = state,
        modifier = Modifier
            .background(
                BudgetGreen,
                RoundedCornerShape(28.dp)
            )
            .height(420.dp),

        title = {
            Text(
                modifier = Modifier
                    .padding(
                        start = 118.dp,
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
            dayInSelectionRangeContainerColor = ExpensesColor,
            dayInSelectionRangeContentColor = Color.White
        ),
        headline = {
            DateRangePickerDefaults.DateRangePickerHeadline(
                selectedStartDateMillis = state.selectedStartDateMillis,
                selectedEndDateMillis = state.selectedEndDateMillis,
                displayMode = state.displayMode,
                dateFormatter = DatePickerDefaults.dateFormatter(selectedDateSkeleton = "dd/MM/yyyy"),
                modifier = Modifier.padding(start = 32.dp)
            )
        }
    )
}