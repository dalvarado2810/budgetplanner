package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.daniel.budgeplanner.MainContract
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.ui.theme.CardColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun ShowNameInScreen(
    name: String,
    event: Flow<MainContract.Event?>,
    onClick: () -> Unit,
    goNextScreen: () -> Unit
) {
    val textName = remember {
        mutableStateOf(name)
    }
    val goNext = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(event) {
        event.onEach { event ->
            if (event is MainContract.Event.SetNewName) {
                textName.value = event.name
                goNext.value = true
            }
        }.collect()
    }

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column {
            Text(text = textName.value,
                modifier = Modifier.padding(bottom = 16.dp))

            ContinueButton(text = stringResource(id = R.string.lets_go)) {
                onClick()
            }

            Spacer(modifier = Modifier.padding(top = 120.dp))

            TextButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                enabled = goNext.value,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Blue,
                    disabledContentColor = Color.Gray,
                    backgroundColor = CardColor,
                    disabledBackgroundColor = CardColor
                ),
                onClick = {
                    goNextScreen()
                }
            ){
                Text(
                    text = stringResource(id = R.string.go_to_first_movement)
                )
            }
        }
    }
}