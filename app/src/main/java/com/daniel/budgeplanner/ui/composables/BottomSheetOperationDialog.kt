package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.data.Category
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.domain.entity.MovementType
import com.daniel.budgeplanner.ui.theme.BudgetGreen
import com.daniel.budgeplanner.utils.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun BottomSheetOperationDialog(
    color: Color,
    saveClick: (movement: Movement) ->  Unit,
    closeClick: () -> Unit
) {

    val title = if (color == BudgetGreen) "INGRESO" else "GASTO"
    val categories = if (color == BudgetGreen) {
        listOf(MONTHLY_INCOMES, OTHER_INCOMES)
    } else {
        listOf(FOOD_EXPENSES, ANT_EXPENSES, SERVICES_EXPENSES )
    }
    val categorySelected = remember { mutableStateOf("") }

    var descriptionText by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var amountText by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var category by remember {
        mutableStateOf(Category.OTHER_INCOMES)
    }

    val isChecked = remember { mutableStateOf(false) }
    val colorCheck = remember { mutableStateOf(Color.Gray) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
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
                modifier = Modifier
                    .padding(vertical = 8.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        }



        IconButton(
            onClick = {
                closeClick()
                categorySelected.value = ""
                      },
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "",
                tint = Color.Black,
            )
        }


        DialogText(
            text = "Descripción",
            size = 18
        )

        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = 2.dp,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            TextField(
                value = descriptionText,
                onValueChange = {
                    if (it.text.length <= 26) descriptionText = it },
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
                )
            )
        }

        DialogText(
            text = "Monto",
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
                    if (it.text.length <= 26) amountText = it },
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
                        contentDescription = "Prefix"
                    )
                }
            )
        }

        DialogText(
            text = "Tipo",
            size = 18
        )

        Column(horizontalAlignment = Alignment.Start) {

            Box(modifier = Modifier
                .fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    categories.forEach { item ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = categorySelected.value == item,
                                onCheckedChange = {
                                    category = item.toCategoryItem()
                                    if (categorySelected.value != item) {
                                        categorySelected.value = item
                                    }

                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = BudgetGreen,
                                    uncheckedColor = Color.Gray
                                )
                            )
                            Text(text = item)
                        }

                    }
                }
            }
        }

        ContinueButton(text = "Guardar") {
            val movement = Movement(
                id = 0,
                movementDescription = descriptionText.text,
                movementAmount = amountText.text.toInt(),
                movementType = if (color == BudgetGreen) MovementType.INCOME else MovementType.EXPENSE,
                movementUser = "Daniel",
                month = 7,
                year = 2023,
                movementCategory = category,
                date = obtainActualDate()
            )
            descriptionText = TextFieldValue("")
            amountText = TextFieldValue("")
            categorySelected.value = ""
            isChecked.value = false
            colorCheck.value = Color.Gray
            saveClick(movement)
        }
    }
}

private fun obtainActualDate(): String {

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    return dateTimeFormatter.format(LocalDate.of(year, month, dayOfMonth))

}




