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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Card
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextButton
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.daniel.budgeplanner.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.MainContract
import com.daniel.budgeplanner.MainViewModel
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.ui.composables.MovementButton
import com.daniel.budgeplanner.ui.composables.TransactionsTextTitle
import com.daniel.budgeplanner.ui.composables.BottomSheetOperationDialog
import com.daniel.budgeplanner.ui.composables.MovementItem
import com.daniel.budgeplanner.ui.composables.NoMovementsItem
import com.daniel.budgeplanner.ui.theme.BackGround
import com.daniel.budgeplanner.ui.theme.CardColor
import com.daniel.budgeplanner.ui.theme.BudgetGreen
import com.daniel.budgeplanner.ui.theme.fonts
import com.daniel.budgeplanner.ui.theme.ExpensesColor
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
    val antExpensesBalance by viewModel.antExpensesBalance.collectAsState(initial = 0)
    val foodExpensesBalance by viewModel.foodExpensesBalance.collectAsState(initial = 0)
    val userName = viewModel.getUserName()
    val myColorState = remember {
        mutableStateOf(Color.Black)
    }
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { false },
        skipHalfExpanded = true
    )
    val listState = rememberLazyListState()
    val isGoToTopEnabled by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }
    val comparator = Comparator<Movement> { a, b -> a.date.compareTo(b.date) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackGround),
    ) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(232.dp),
            shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp),
            backgroundColor = BudgetGreen,
            elevation = 8.dp
        ) { }

        Column{

            Row {
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
                        .padding(start = 4.dp, top = 10.dp),
                    text = userName,
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                )
            }

            Text(
                modifier = Modifier
                    .padding(start = 16.dp, top = 6.dp),
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

            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    start = 58.dp)
            ){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = stringResource(id = R.string.food_balance),
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold)
                    )

                    Text(
                        modifier = Modifier,
                        text = toFormattedAmount(foodExpensesBalance),
                        color = setActualBalanceColor(foodExpensesBalance),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fonts),
                    )
                }

                Spacer(
                    modifier = Modifier
                        .width(58.dp)
                )

                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.ant_balance),
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold)
                    )

                    Text(
                        modifier = Modifier,
                        text = toFormattedAmount(antExpensesBalance),
                        color = setActualBalanceColor(antExpensesBalance),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fonts),
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(
                    start = 66.dp,
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
                        .width(68.dp)
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
                        .height(380.dp),
                    state = listState
                ) {
                    items(movementsList.sortedWith(comparator)) { item ->
                        MovementItem(item = item.toMovementItem())
                    }
                }
            }

            TextButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                enabled = isGoToTopEnabled,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Blue,
                    disabledContentColor = Color.Gray,
                    backgroundColor = BackGround,
                    disabledBackgroundColor = BackGround
                ),
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                }
            ){
                Text(
                    text = stringResource(id = R.string.go_to_first_movement)
                )
            }
        }

        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetContent = {
                BottomSheetOperationDialog(
                    user = userName,
                    color = myColorState.value,
                    closeClick = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }
                    },
                    saveClick = { movement ->
                        coroutineScope.launch {
                            viewModel.setAction(action = MainContract.Action.AddMovements(movement))
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
    numberFormat.maximumFractionDigits = 0
    return numberFormat.format(amount)
}