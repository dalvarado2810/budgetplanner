package com.daniel.budgetplanner.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgetplanner.R
import com.daniel.budgetplanner.ui.composables.ContinueButton
import com.daniel.budgetplanner.ui.composables.InputTextField
import com.daniel.budgetplanner.ui.composables.MyDateRangePickerDialog
import com.daniel.budgetplanner.ui.composables.NameMissedDialog
import com.daniel.budgetplanner.ui.composables.TopShape
import com.daniel.budgetplanner.ui.theme.BackGround
import com.daniel.budgetplanner.ui.theme.CardColor
import com.daniel.budgetplanner.ui.theme.fonts
import com.daniel.budgetplanner.utils.EMPTY_STRING
import com.daniel.budgetplanner.utils.IMAGE
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GetStarted (
    dateSelectionAction: (LocalDate?, LocalDate?, String) -> Unit
) {
    val showDatePicker = remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(EMPTY_STRING) }
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()
    val buttonEnabled = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = BackGround)
    ) {
        TopShape() {
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 65.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, bottom = 6.dp),
                text = stringResource(id = R.string.your_budget),
                color = Color.Black,
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontFamily = fonts,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            InputTextField() {
                buttonEnabled.value = it != EMPTY_STRING
                name = it
            }

            Image(
                painter = painterResource(id = R.drawable.salary_day),
                contentDescription = IMAGE,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 235.dp)
                    .padding(
                        top = 6.dp,
                        bottom = 6.dp
                    )
            )

            Text(
                modifier = Modifier.padding(horizontal = 6.dp),
                text = stringResource(id = R.string.regulate_expenses),
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = fonts,
                    fontSize = 22.sp
                )
            )

            if(showDatePicker.value){
                MyDateRangePickerDialog(
                    onRangeDatesSelected = { star, end ->
                        if (star == end) {
                            coroutineScope.launch { bottomSheetState.show() }
                        } else {
                            dateSelectionAction(star, end, name)
                        }
                    },
                    onDismiss = {
                        showDatePicker.value = false
                    }
                )
            }

            Text(
                text = stringResource(id = R.string.we_can_help),
                color = Color.Gray,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ), modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 22.dp,
                        end = 22.dp,
                        top = 6.dp,
                        bottom = 20.dp
                    )
            )
            ContinueButton(enabled = buttonEnabled.value, text = stringResource(id = R.string.lets_begin)) {
                if (name.isNotEmpty()) {
                    showDatePicker.value = true
                } else {
                    coroutineScope.launch { bottomSheetState.show() }
                }
            }
        }

        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetContent = {
                    NameMissedDialog {
                        coroutineScope.launch { bottomSheetState.hide() }
                    }
                },
            sheetBackgroundColor = CardColor,
            sheetShape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp),
            sheetElevation = 6.dp,
        ) { }
    }
}

@Preview
@Composable
fun StartPage(){
    GetStarted (
        dateSelectionAction = { start, end , name ->
        }
    )
}