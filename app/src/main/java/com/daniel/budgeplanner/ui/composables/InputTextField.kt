package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.daniel.budgeplanner.ui.theme.BackGround
import com.daniel.budgeplanner.ui.theme.BudgetGreen
import com.daniel.budgeplanner.utils.EMPTY_STRING

@Composable
fun InputTextField(
    saveName: (name: String) -> Unit
) {
    var text by remember {
        mutableStateOf(TextFieldValue(EMPTY_STRING))
    }

   TextField(
        value = text,
        singleLine = true,
        onValueChange = {
            if (it.text.isEmpty()) saveName(EMPTY_STRING)
            if (it.text.length <= 30) {
                text = it
                saveName(it.text)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = BudgetGreen,
            cursorColor = BudgetGreen
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
           imeAction = ImeAction.Done
        )
    )
}

@Preview
@Composable
fun Star() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
    ) {
        InputTextField() {

        }
    }


}