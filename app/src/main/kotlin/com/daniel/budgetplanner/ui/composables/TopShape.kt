package com.daniel.budgetplanner.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgetplanner.R
import com.daniel.budgetplanner.ui.theme.BackGround
import com.daniel.budgetplanner.ui.theme.OnboardingBackground
import com.daniel.budgetplanner.utils.EMPTY_STRING
import com.daniel.budgetplanner.utils.IMAGE
import com.daniel.budgetplanner.utils.STEP_ONE
import com.daniel.budgetplanner.utils.STEP_THREE
import com.daniel.budgetplanner.utils.STEP_TWO

@Composable
fun TopShape(
    title: String = EMPTY_STRING,
    step: Int? = null,
    isOnboarding: Boolean = false
) {
    val backGround = if (isOnboarding) OnboardingBackground else BackGround
    val moneyTopImg = if (isOnboarding) R.drawable.money_top else R.drawable.splash
    val image = when(step){
        STEP_ONE -> R.drawable.money_card
        STEP_TWO -> R.drawable.categories_top_image
        STEP_THREE -> R.drawable.secure_info
        else -> null
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backGround)
    ) {
        Image(
            painter = painterResource(id = moneyTopImg),
            contentDescription = IMAGE,
            modifier = Modifier
                .width(width = 218.dp)
                .height(height = 172.dp),
        )
        if (image !=  null) {
            when (step){
                STEP_ONE -> {
                    ImageSteps(image = image, pt = 80.dp, pb = 0.dp, ps = 60.dp, width = 260.dp , height = 245.dp)
                }
                STEP_TWO -> {
                    ImageSteps(image = image, pt = 30.dp, pb =  25.dp, ps = 60.dp, width = 260.dp, height = 263.dp)
                }
                STEP_THREE -> {
                    ImageSteps(image = image, pt = 0.dp, pb =  0.dp, ps = 40.dp, width = 347.dp, height = 328.dp)
                }
                else -> { }
            }
        }

        Text(
            text = title,
            color = Color.Black,
            textAlign = TextAlign.End,
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                lineBreak = LineBreak.Heading,
                letterSpacing = 2.sp
            ),
            modifier = Modifier
                .padding(
                    top = 80.dp,
                    end = 12.dp
                )
        )
    }
}