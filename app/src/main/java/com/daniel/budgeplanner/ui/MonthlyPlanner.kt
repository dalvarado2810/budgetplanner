package com.daniel.budgeplanner.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.daniel.budgeplanner.MainContract
import com.daniel.budgeplanner.MainViewModel
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.ui.composables.TopShape
import com.daniel.budgeplanner.ui.composables.MovementButton
import com.daniel.budgeplanner.ui.composables.TransactionsTextTitle
import com.daniel.budgeplanner.ui.composables.BottomSheetOperationDialog
import com.daniel.budgeplanner.ui.composables.MovementItem
import com.daniel.budgeplanner.ui.composables.NoMovementsItem
import com.daniel.budgeplanner.ui.composables.ContinueButton
import com.daniel.budgeplanner.ui.theme.BudgetGreen
import com.daniel.budgeplanner.ui.theme.CardColor
import com.daniel.budgeplanner.ui.theme.ExpensesColor
import com.daniel.budgeplanner.utils.ScreensNavigation
import com.daniel.budgeplanner.utils.toMovementItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MonthlyPlanner(
    navController: NavController,
    viewModel: MainViewModel){

    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val itemList by viewModel.monthlyMovements.collectAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { false },
        skipHalfExpanded = true
    )
    val myColorState = remember {
        mutableStateOf(Color.Black)
    }

    when(viewState.screenState) {
        is MainContract.ScreenState.Success -> {
            showToast(context, (viewState.screenState as MainContract.ScreenState.Success).data)
            viewModel.setEvent(event = MainContract.Event.setSuccessState)
        }
        is MainContract.ScreenState.initial -> {}
        is MainContract.ScreenState.Loading -> {}
        is MainContract.ScreenState.error -> {}
    }

    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xfff0f4f3))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopShape(stringResource(id = R.string.plan_your_budget))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(id = R.string.plan_your_budget_instructions),
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                Row(
                    modifier = Modifier
                        .padding(
                            horizontal = 10.dp,
                            vertical = 10.dp
                        )
                ) {

                    MovementButton(
                        icon = R.drawable.income_icon,
                        text = stringResource(id = R.string.incomes)
                    ) {
                        myColorState.value = BudgetGreen
                        coroutineScope.launch { modalBottomSheetState.show() }
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
                        coroutineScope.launch { modalBottomSheetState.show() }
                    }
                }

                TransactionsTextTitle()

                if (itemList.isEmpty()) {
                    NoMovementsItem()
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                    ) {
                        items(itemList) { item ->
                            MovementItem(item = item.toMovementItem())
                        }
                    }
                }
            }
        }

        Column(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 12.dp)
            .height(60.dp)) {
            ContinueButton(text = stringResource(id = R.string.calculate_your_budget)) {
                navController.navigate(ScreensNavigation.BudgetDashboard.routes)
            }
        }

        ModalBottomSheetLayout(
            sheetState = modalBottomSheetState,
            sheetContent = {
                BottomSheetOperationDialog(
                    user = viewModel.getUserName(),
                    color = myColorState.value,
                    closeClick = {
                        coroutineScope.launch {
                            modalBottomSheetState.hide()
                        }
                    },
                    saveClick = { movement ->
                        coroutineScope.launch {
                            viewModel.setEvent(event = MainContract.Event.AddMovements(movement))
                            modalBottomSheetState.hide()
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

private fun showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}