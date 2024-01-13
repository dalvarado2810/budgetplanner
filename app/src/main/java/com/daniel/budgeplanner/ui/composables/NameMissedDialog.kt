package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.ui.theme.BudgetGreen
import com.daniel.budgeplanner.ui.theme.OnboardingBackground
import com.daniel.budgeplanner.ui.theme.fonts

@Composable
fun NameMissedDialog(
    onClickAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(OnboardingBackground)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.name_warning),
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontFamily = fonts,
                fontSize = 22.sp
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onClickAction,
            elevation = ButtonDefaults.elevation(
                defaultElevation = 2.dp
            ),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BudgetGreen,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(width = 145.dp)
                .height(height = 40.dp),
            contentPadding = PaddingValues(
                start = 32.dp,
                end = 32.dp
            ),
            enabled = true
        ) {
            Text(
                text = stringResource(id = R.string.close),
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 19.sp
                )
            )
        }
    }
}

@Preview
@Composable
fun Start(){
    Box(modifier = Modifier
        .background(Color.White)
    ) {
        NameMissedDialog { }
    }
}