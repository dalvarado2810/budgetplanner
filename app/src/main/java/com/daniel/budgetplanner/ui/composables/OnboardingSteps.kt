package com.daniel.budgetplanner.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.daniel.budgetplanner.R
import com.daniel.budgetplanner.data.Steps
import com.daniel.budgetplanner.ui.theme.fonts
import com.daniel.budgetplanner.utils.IMAGE_STEP_IMAGE

@Composable
fun OnboardingSteps(
    step: Steps,
    continueAction: () -> Unit
) {
    val title = when (step){
        Steps.STEP_ONE -> R.string.onboarding_step_one_title
        Steps.STEP_TWO -> R.string.onboarding_step_two_title
        Steps.STEP_THREE -> R.string.onboarding_step_three_title
    }
    val subtitle = when (step){
        Steps.STEP_ONE -> R.string.onboarding_step_one_subtitle
        Steps.STEP_TWO -> R.string.onboarding_step_two_subtitle
        Steps.STEP_THREE -> R.string.onboarding_step_three_subtitle
    }
    val stepImage = when (step){
        Steps.STEP_ONE -> R.drawable.step_1
        Steps.STEP_TWO -> R.drawable.step_2
        Steps.STEP_THREE -> R.drawable.step_3
    }

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
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ){
                TopShape(
                    isOnboarding = true,
                    step = step.step
                )
            }
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        text = stringResource(id = title),
                        color = Color.Black,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontFamily = fonts,
                            fontSize = 24.sp)
                    )

                    Text(
                        text = stringResource(id = subtitle),
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
                }
            }
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = stepImage),
                        contentDescription = IMAGE_STEP_IMAGE,
                        modifier = Modifier
                            .padding(top = 36.dp)
                            .width(width = 74.dp)
                            .height(height = 24.dp)
                    )

                    OnboardingNextStepButton {
                        continueAction()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewExample() {
    OnboardingSteps(
        step = Steps.STEP_ONE,
    ) { }
}