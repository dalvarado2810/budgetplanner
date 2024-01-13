package com.daniel.budgeplanner.ui

import android.content.Intent
import androidx.compose.foundation.Image
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
import androidx.compose.material.DropdownMenu
import androidx.compose.material.IconButton
import androidx.compose.material.TextButton
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import com.daniel.budgeplanner.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.daniel.budgeplanner.MainActivity
import com.daniel.budgeplanner.MainContract
import com.daniel.budgeplanner.MainViewModel
import com.daniel.budgeplanner.data.DashboardBalances
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.ui.composables.BalancesStatusBar
import com.daniel.budgeplanner.ui.composables.MovementButton
import com.daniel.budgeplanner.ui.composables.BottomSheetOperationDialog
import com.daniel.budgeplanner.ui.composables.MovementItem
import com.daniel.budgeplanner.ui.composables.MyDateRangePickerDialog
import com.daniel.budgeplanner.ui.composables.NoMovementsItem
import com.daniel.budgeplanner.ui.theme.BackGround
import com.daniel.budgeplanner.ui.theme.CardColor
import com.daniel.budgeplanner.ui.theme.BudgetGreen
import com.daniel.budgeplanner.ui.theme.fonts
import com.daniel.budgeplanner.ui.theme.ExpensesColor
import com.daniel.budgeplanner.utils.EMPTY_STRING
import com.daniel.budgeplanner.utils.changeDateFormat
import com.daniel.budgeplanner.utils.toMovementItem
import kotlinx.coroutines.launch
import java.text.NumberFormat

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BudgetDashboard(
    viewModel: MainViewModel
) {

    val actualBalance by viewModel.actualBalance.collectAsState(initial = DashboardBalances())
    val movementsList by viewModel.monthlyMovements.collectAsState(initial = emptyList())
    val userName = viewModel.getUserName()
    val dates = viewModel.getRangeOfDates()
    val myColorState = remember {
        mutableStateOf(Color.Black)
    }
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { false },
        skipHalfExpanded = true
    )
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val isGoToTopEnabled by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }
    val comparator = Comparator<Movement> { a, b -> a.date.compareTo(b.date) }
    val expanded = remember { mutableStateOf(false) }
    val showDatePicker = remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackGround),
    ) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(317.dp),
            shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp),
            backgroundColor = BudgetGreen,
            elevation = 8.dp
        ) { }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Row (modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
            ){
                Text(
                    modifier = Modifier
                        .weight(3f)
                        .padding(start = 24.dp, top = 16.dp),
                    text = stringResource(id = R.string.hello),
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light)
                )

                Text(
                    modifier = Modifier
                        .weight(15f)
                        .padding(top = 10.dp),
                    text = userName,
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                )

                DropdownMenu(
                    modifier = Modifier
                        .background(BackGround),
                    offset = DpOffset(248.dp, 0.dp),
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    DropdownMenuItem(
                        onClick = {
                            viewModel.setAction(MainContract.Action.AddName(EMPTY_STRING))
                            val intent = Intent(context, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(context, intent, null)
                        },
                        text = {
                            Text(
                                text = stringResource(id = R.string.erase_user),
                                fontSize = 12.sp,
                                fontFamily = fonts)
                        }
                    )
                    DropdownMenuItem(
                        onClick = {
                            showDatePicker.value = true
                            expanded.value = false
                        },
                        text = {
                            Text(
                                text = stringResource(id = R.string.change_date),
                                fontSize = 12.sp,
                                fontFamily = fonts)
                        }
                    )

                }

                IconButton(
                    onClick = {
                        expanded.value = !expanded.value
                    },
                    modifier = Modifier.weight(3f)
                ) {
                    Image(painterResource(id = R.drawable.baseline_menu_24), contentDescription = "Dropdown Icon")
                }
            }

            Text(
                modifier = Modifier
                    .padding(top = 6.dp),
                text = stringResource(id = R.string.your_balance),
                color = Color.Black,
                style = LocalTextStyle.current.merge(
                    TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both
                        )
                    )
                )
            )

            Text(
                modifier = Modifier
                    .height(46.dp),
                text = toFormattedAmount(actualBalance.actualBalance),
                color = setActualBalanceColor(actualBalance.actualBalance),
                style = TextStyle(
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fonts),
            )

            BalancesStatusBar(
                monthlyBalance = actualBalance.monthlyIncomeBalance,
                otherBalance = actualBalance.otherIncomesBalance,
                foodBalance = actualBalance.foodExpensesBalance,
                healthBalance = actualBalance.healthExpensesBalance,
                servicesBalance = actualBalance.servicesExpensesBalance,
                transportBalance = actualBalance.transportExpensesBalance,
                outfitBalance = actualBalance.outfitExpensesBalance,
                antBalance = actualBalance.antExpensesBalance
            )

            if(showDatePicker.value){
                MyDateRangePickerDialog(
                    onRangeDatesSelected = { start, end ->
                        viewModel.setAction(MainContract.Action.SetRangeDate(
                            startDate = start,
                            endDate = end)
                        )
                        val intent = Intent(context, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(context, intent, null)
                    },
                    onDismiss = {
                        showDatePicker.value = false
                    }
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth()
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
                        .width(38.dp)
                )

                MovementButton(
                    icon = R.drawable.outcome_icon,
                    text = stringResource(id = R.string.outcomes)
                ) {
                    myColorState.value = ExpensesColor
                    coroutineScope.launch { bottomSheetState.show() }
                }
            }

            Text(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 6.dp),
                text = stringResource(id = R.string.dates,
                    dates.first.changeDateFormat(),
                    dates.second.changeDateFormat()),
                color = Color.Black,
                style = LocalTextStyle.current.merge(
                    TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
            )

            if (movementsList.isEmpty()) {
                NoMovementsItem()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp),
                    state = listState
                ) {
                    items(movementsList.sortedWith(comparator)) { item ->
                        MovementItem(item = item.toMovementItem())
                    }
                }
            }


                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .align(Alignment.CenterHorizontally),
                    enabled = isGoToTopEnabled,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Blue,
                        disabledContentColor = Color.Gray,
                        backgroundColor = BudgetGreen,
                        disabledBackgroundColor = BudgetGreen
                    ),
                    shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
                    onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(0)
                        }
                    }
                ) {
                    if (isGoToTopEnabled) {
                        Text(
                            text = stringResource(id = R.string.go_to_first_movement)
                        )
                    }
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