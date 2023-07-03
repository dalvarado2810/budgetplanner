package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.ui.theme.BudgetGreen

@Composable
fun InputTextField(
    title: String,
    saveName: (name: String) -> Unit
) {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }

    OutlinedTextField(
        value = text,
        label = {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        },
        onValueChange = {
            if (it.text.length <= 30) {
                text = it
                saveName(it.text)
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BudgetGreen,
            focusedLabelColor = Color.Black,
            unfocusedBorderColor = BudgetGreen,
            unfocusedLabelColor = Color.Gray,
        ),
        enabled = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 10.dp,
                horizontal = 12.dp
            ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    )
}