package com.daniel.budgetplanner.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.Surface
import androidx.compose.material.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.Card
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.daniel.budgetplanner.R
import com.daniel.budgetplanner.data.Category
import com.daniel.budgetplanner.domain.entity.Movement
import com.daniel.budgetplanner.domain.entity.MovementType
import com.daniel.budgetplanner.ui.showToast
import com.daniel.budgetplanner.ui.theme.BudgetGreen
import com.daniel.budgetplanner.utils.MONTHLY_INCOMES
import com.daniel.budgetplanner.utils.OTHER_INCOMES
import com.daniel.budgetplanner.utils.ANT_EXPENSES
import com.daniel.budgetplanner.utils.DEFAULT_CATEGORY
import com.daniel.budgetplanner.utils.FOOD_EXPENSES
import com.daniel.budgetplanner.utils.SERVICES_EXPENSES
import com.daniel.budgetplanner.utils.EMPTY_STRING
import com.daniel.budgetplanner.utils.HEALTH_EXPENSES
import com.daniel.budgetplanner.utils.ICON
import com.daniel.budgetplanner.utils.OUTFIT_EXPENSES
import com.daniel.budgetplanner.utils.TRANSPORTATION_EXPENSES
import com.daniel.budgetplanner.utils.toCategoryItem
import com.daniel.budgetplanner.utils.toViewPattern
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun BottomSheetOperationDialog(
    user: String,
    color: Color,
    saveClick: (movement: Movement) ->  Unit,
    closeClick: () -> Unit
) {

    val context = LocalContext.current
    val toastMessage = stringResource(id = R.string.add_movement_amount)
    val title = if (color == BudgetGreen) stringResource(id = R.string.income)
    else stringResource(id = R.string.outcome)
    val categories = if (color == BudgetGreen) {
        listOf(MONTHLY_INCOMES, OTHER_INCOMES)
    } else {
        listOf(
            FOOD_EXPENSES,
            ANT_EXPENSES,
            SERVICES_EXPENSES,
            OUTFIT_EXPENSES,
            TRANSPORTATION_EXPENSES,
            HEALTH_EXPENSES
        )
    }
    val categorySelected = remember { mutableStateOf(EMPTY_STRING) }

    var descriptionText by remember {
        mutableStateOf(TextFieldValue(EMPTY_STRING))
    }

    var amountText by remember {
        mutableStateOf(TextFieldValue(EMPTY_STRING))
    }

    var category by remember {
        mutableStateOf(Category.OTHER_INCOMES)
    }

    val isChecked = remember { mutableStateOf(false) }
    val buttonEnabled = remember { mutableStateOf(false) }
    val colorCheck = remember { mutableStateOf(Color.Gray) }
    val showDatePicker = remember { mutableStateOf(false) }
    val dateSelected = remember { mutableStateOf(LocalDate.now()) }
    val keyboardController = LocalSoftwareKeyboardController.current

    var isExpanded by remember {
        mutableStateOf(false)
    }

    val selectedTemp = remember {
        mutableStateOf(DEFAULT_CATEGORY)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier
            .padding(bottom = 12.dp)
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            color = color,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            Text(
                text = title,
                color = Color.Black,
                modifier = Modifier
                    .padding(vertical = 8.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            DialogText(
                text = stringResource(id = R.string.description),
                size = 18
            )
            Spacer(modifier = Modifier.width(32.dp))
            IconButton(
                onClick = {
                    closeClick()
                    categorySelected.value = EMPTY_STRING
                },
                modifier = Modifier
                    .padding(start = 64.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = ICON,
                    tint = Color.Black,
                )
            }
        }

        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = 2.dp,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            TextField(
                value = descriptionText,
                onValueChange = {
                    if (it.text.length <= 26) descriptionText = it
                },
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    cursorColor = Color.White,
                    backgroundColor = color
                ),
                singleLine = true,
                modifier = Modifier
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                )
            )
        }

        DialogText(
            text = stringResource(id = R.string.amount),
            size = 18
        )

        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = 2.dp,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            TextField(
                value = amountText,
                onValueChange = {
                    if (it.text.length <= 26 &&
                        it.text.isDigitsOnly()
                    ) amountText = it
                },
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    cursorColor = Color.White,
                    backgroundColor = color
                ),
                modifier = Modifier
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.dollar),
                        contentDescription = ICON,
                        tint = Color.Black,
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = VisualTransformation.None
            )
        }

        Button(
            onClick = { showDatePicker.value = true },
            elevation = androidx.compose.material.ButtonDefaults.elevation(
                defaultElevation = 2.dp
            ),
            shape = RoundedCornerShape(corner = CornerSize(48.dp)),
            colors = androidx.compose.material.ButtonDefaults.buttonColors(
                backgroundColor = color,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .width(width = 155.dp)
                .height(height = 40.dp)
                .padding(top = 4.dp, bottom = 4.dp),
            contentPadding = PaddingValues(
                top = 4.dp,
                start = 12.dp,
                end = 12.dp
            )
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.baseline_date_range_24
                ),
                contentDescription = ICON,
                modifier = Modifier.padding(end = 6.dp),
                alignment = Alignment.Center
            )
            Text(text = dateSelected.value.toViewPattern())
        }

        Column(
            modifier = Modifier.padding(bottom = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            androidx.compose.material3.ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = !isExpanded },
            ) {
                androidx.compose.material3.TextField(
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    value = selectedTemp.value,
                    onValueChange = {},
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        unfocusedContainerColor = color,
                        focusedContainerColor = color,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false },
                ) {
                    categories.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(
                                text = selectionOption,
                                color = Color.Black)
                                   },
                            onClick = {
                                category = selectionOption.toCategoryItem()
                                selectedTemp.value = selectionOption
                                categorySelected.value = selectionOption
                                isExpanded = false
                                keyboardController?.hide()
                            },
                        )
                    }
                }
            }
        }

        if (showDatePicker.value) {
            MyDatePickerDialog(
                onDateSelected = { dateSelected.value = it }, {
                    showDatePicker.value = false
                }
            )
        }

        buttonEnabled.value = descriptionText.text.isNotEmpty() &&
                amountText.text.isNotEmpty() &&
                categorySelected.value.isNotEmpty()

        ContinueButton(
            text = stringResource(id = R.string.save_movement),
            color = color,
            enabled = buttonEnabled.value
        ) {
            val amount = amountText.text.toInt()
            val movement = Movement(
                id = 0,
                movementDescription = descriptionText.text,
                movementAmount = amount,
                movementType = if (color == BudgetGreen) MovementType.INCOME else MovementType.EXPENSE,
                movementUser = user,
                month = dateSelected.value.monthValue,
                year = dateSelected.value.year,
                movementCategory = category,
                date = dateSelected.value
            )
            descriptionText = TextFieldValue(EMPTY_STRING)
            amountText = TextFieldValue(EMPTY_STRING)
            categorySelected.value = EMPTY_STRING
            selectedTemp.value = DEFAULT_CATEGORY
            isChecked.value = false
            colorCheck.value = Color.Gray
            if (amount != 0) {
                saveClick(movement)
            } else {
                showToast(context, toastMessage)
            }
        }
    }
}