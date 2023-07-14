package com.daniel.budgeplanner.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.ui.composables.ContinueButton
import com.daniel.budgeplanner.ui.composables.TopShape
import com.daniel.budgeplanner.ui.theme.BackGround
import com.daniel.budgeplanner.utils.IMAGE

@Composable
fun GetStarted (
    onClickContinue: () -> Unit
) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackGround)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            TopShape()
            Text(
                text = stringResource(id = R.string.your_budget),
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(id = R.drawable.true_friends),
                contentDescription = IMAGE,
                modifier = Modifier
                    .width(width = 290.dp)
                    .height(height = 235.dp)
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp
                    )
            )

            Text(
                text = stringResource(id = R.string.regulate_expenses),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 22.sp
                )
            )

            Text(
                text = stringResource(id = R.string.we_can_help),
                color = Color.Black.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                ), modifier = Modifier
                    .width(160.dp)
                    .padding(
                        top = 6.dp,
                        bottom = 120.dp
                    )
            )
            ContinueButton(text = stringResource(id = R.string.lets_begin)) {
                onClickContinue()
            }
        }
    }
}