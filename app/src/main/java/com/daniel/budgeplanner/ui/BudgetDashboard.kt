package com.daniel.budgeplanner.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.daniel.budgeplanner.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.MainViewModel
import com.daniel.budgeplanner.ui.composables.*
import com.daniel.budgeplanner.ui.theme.*
import com.daniel.budgeplanner.utils.toMovementItem
import kotlinx.coroutines.launch
import java.text.NumberFormat

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BudgetDashboard(
    viewModel: MainViewModel
) {
    val movementsList by viewModel.monthlyMovements.collectAsState(initial = emptyList())
    val actualBalance by viewModel.actualBalance.collectAsState(initial = 0)
    val myColorState = remember {
        mutableStateOf(Color.Black)
    }
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { false },
        skipHalfExpanded = true
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackGround),
    ) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(228.dp),
            shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp),
            backgroundColor = BudgetGreen,
            elevation = 8.dp
        ) { }

        Column{

            Text(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp),
                text = stringResource(id = R.string.hello),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light)
            )

            Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = viewModel.getUserName(),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
            )

            Text(
                modifier = Modifier
                    .padding(start = 16.dp, top = 12.dp),
                text = stringResource(id = R.string.your_balance),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold)
            )

            Text(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.Start),
                text = toFormattedAmount(actualBalance),
                color = setActualBalanceColor(actualBalance),
                style = TextStyle(
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fonts),

            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(
                    start = 82.dp,
                    top = 5.dp)
            ) {
                MovementButton(
                    icon = R.drawable.income_icon,
                    text = stringResource(id = R.string.incomes)
                ) {
                    myColorState.value = BudgetGreen
                    coroutineScope.launch { bottomSheetState.show() }
                }

                Spacer(
                    modifier = Modifier
                        .width(32.dp)
                )

                MovementButton(
                    icon = R.drawable.outcome_icon,
                    text = stringResource(id = R.string.outcomes)
                ) {
                    myColorState.value = ExpensesColor
                    coroutineScope.launch { bottomSheetState.show() }
                }
            }

            TransactionsTextTitle()

            if (movementsList.isEmpty()) {
                NoMovementsItem()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                ) {
                    items(movementsList) { item ->
                        MovementItem(item = item.toMovementItem())
                    }
                }
            }
        }

        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetContent = {
                BottomSheetOperationDialog(
                    color = myColorState.value,
                    closeClick = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }
                    },
                    saveClick = { movement ->
                        coroutineScope.launch {
                            viewModel.addMovement(movement)
                            bottomSheetState.hide()
                        }
                    }
                )
            },
            sheetBackgroundColor = CardColor,
            sheetShape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp),
            sheetElevation = 6.dp,
        ) { }
    }
}

fun setActualBalanceColor(actualBalance: Int): Color {
    return if (actualBalance < 0) Color.Red else Color.Black
}

fun toFormattedAmount(amount: Int): String {
    val numberFormat = NumberFormat.getCurrencyInstance()
    return numberFormat.format(amount)
}