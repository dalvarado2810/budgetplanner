package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.ui.theme.fonts
import com.daniel.budgeplanner.utils.IMAGE

@Composable
fun OnboardingStepTwo() {

    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xffDCF1DE))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            TopShape(
                isOnboarding = true,
                step = 2
            ) {}

            Text(
                text = stringResource(id = R.string.onboarding_step_two_title),
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = fonts,
                    fontSize = 26.sp)
            )

            Text(
                text = stringResource(id = R.string.onboarding_step_one_subtitle),
                color = Color.Black.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = fonts,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                ), modifier = Modifier
                    .width(260.dp)
                    .padding(
                        top = 16.dp,
                        bottom = 20.dp
                    )
            )

            Image(
                painter = painterResource(id = R.drawable.step_2),
                contentDescription = IMAGE,
                modifier = Modifier
                    .width(width = 74.dp)
                    .height(height = 24.dp)
            )

            OnboardingNextStepButton() {

            }
        }
    }
}

@Preview
@Composable
fun PreviewExampleTwo() {
    OnboardingStepTwo()
}