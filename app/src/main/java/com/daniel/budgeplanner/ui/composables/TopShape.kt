package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.ui.theme.BackGround
import com.daniel.budgeplanner.ui.theme.BudgetGreen
import com.daniel.budgeplanner.ui.theme.OnboardingBackground
import com.daniel.budgeplanner.utils.EMPTY_STRING
import com.daniel.budgeplanner.utils.ICON
import com.daniel.budgeplanner.utils.IMAGE
import com.daniel.budgeplanner.utils.STEP_ONE
import com.daniel.budgeplanner.utils.STEP_THREE
import com.daniel.budgeplanner.utils.STEP_TWO

@Composable
fun TopShape(
    title: String = EMPTY_STRING,
    step: Int? = null,
    isOnboarding: Boolean = false,
    buttonVisible: Boolean = false,
    onClick: () -> Unit
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
                    ImageSteps(image = image, pt = 120.dp, pb = 0.dp, width = 360.dp , height = 345.dp)
                }
                STEP_TWO -> {
                    ImageSteps(image = image, pt = 30.dp, pb =  25.dp, width = 360.dp, height = 363.dp)
                }
                STEP_THREE -> {
                    ImageSteps(image = image, pt = 0.dp, pb =  0.dp, width = 447.dp, height = 428.dp)
                }
                else -> {

                }
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
        if (buttonVisible) {
            Row ( modifier = Modifier
                .align(Alignment.TopEnd)
            ){
                Text(
                    text = stringResource(id = R.string.select_range_of_date),
                    color = Color.Black,
                    textAlign = TextAlign.End,
                    style = TextStyle(
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        lineBreak = LineBreak.Heading,
                        letterSpacing = 2.sp
                    ),
                    modifier = Modifier
                        .padding(end = 8.dp, top = 24.dp)
                        .width(140.dp)
                )
                Button(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .width(48.dp),
                    onClick = { onClick() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = BudgetGreen
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = ICON
                    )
                }
            }
        }
    }
}