package com.daniel.budgetplanner.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.daniel.budgetplanner.MainContract
import com.daniel.budgetplanner.MainViewModel
import com.daniel.budgetplanner.R
import com.daniel.budgetplanner.ui.composables.ContinueButton
import com.daniel.budgetplanner.ui.composables.InputTextField
import com.daniel.budgetplanner.ui.composables.TopShape
import com.daniel.budgetplanner.utils.EMPTY_STRING
import com.daniel.budgetplanner.utils.ICON_ONBOARDING
import com.daniel.budgetplanner.utils.ICON_RIGHT
import com.daniel.budgetplanner.utils.ScreensNavigation

@Composable
fun Onboarding(
    navController: NavController,
    viewModel: MainViewModel
) {
    var name by remember { mutableStateOf(EMPTY_STRING) }

    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xfff0f4f3))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            TopShape(stringResource(id = R.string.onboarding_step_one_subtitle))

            InputTextField {
                name = it
            }

            Text(
                text = stringResource(id = R.string.add_operations),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    lineBreak = LineBreak.Paragraph,
                    lineHeight = 20.sp
                ),
                modifier = Modifier
                    .padding(
                        vertical = 8.dp,
                        horizontal = 12.dp
                    )
            )

            CategoryText(
                text = stringResource(id = R.string.expenses_categories_divided_to))

            LeftRow(
                resourceId = R.drawable.shopping_cart,
                titleText = stringResource(id = R.string.food_expenses),
                subTitleText = stringResource(id = R.string.food_expenses_description)
            )

            RightRow(
                resourceId = R.drawable.money,
                titleText = stringResource(id = R.string.ant_expenses),
                subTitleText = stringResource(id = R.string.ant_expenses_description)
            )

            LeftRow(
                resourceId = R.drawable.services,
                titleText = stringResource(id = R.string.services_expenses),
                subTitleText = stringResource(id = R.string.services_expenses_description)
            )

            CategoryText(text = stringResource(id = R.string.incomes_categories_divided_to))

            RightRow(
                resourceId = R.drawable.finance_icon,
                titleText = stringResource(id = R.string.monthly_incomes),
                subTitleText = stringResource(id = R.string.monthly_incomes_description)
            )

            LeftRow(
                resourceId = R.drawable.various_input,
                titleText = stringResource(id = R.string.various_incomes),
                subTitleText = stringResource(id = R.string.various_incomes_description)
            )

            Text(
                text = stringResource(id = R.string.lets_begin_planning),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp
                ),
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp,
                        end = 12.dp,
                        start = 12.dp
                    )
            )

            ContinueButton(text = stringResource(id = R.string.lets_go)) {
                viewModel.setAction(MainContract.Action.AddName(name))
                navController.navigate(ScreensNavigation.MonthlyPlanner.routes)
            }
        }
    }
}

@Composable
fun LeftRow(
    resourceId: Int,
    titleText: String,
    subTitleText: String
) {
    Row(
        modifier = Modifier
            .padding(
                top = 12.dp,
                end = 36.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = ICON_ONBOARDING,
            modifier = Modifier
                .padding()
                .height(50.dp)
                .width(50.dp))
        Column(modifier = Modifier
            .padding(
                start = 14.dp
            )) {
            Text(
                text = titleText,
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp),
            )
            Text(
                text = subTitleText,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 13.sp),
                modifier = Modifier
                    .width(250.dp))
        }
    }
}

@Composable
fun RightRow(
    resourceId: Int,
    titleText: String,
    subTitleText: String
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding( top = 12.dp)
    ) {
        Column (
            modifier = Modifier
                .padding( end = 8.dp)
                ) {
            Text(
                text = titleText,
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp),
            )
            Text(
                text = subTitleText,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 13.sp),
                modifier = Modifier
                    .width(290.dp))
        }
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = ICON_RIGHT,
            modifier = Modifier
                .height(50.dp)
                .width(50.dp))
    }
}

@Composable
fun CategoryText(
    text: String
) {
    Text(
        text = text,
        color = Color.Black,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineBreak = LineBreak.Heading,
            lineHeight = 20.sp
        ),
        modifier = Modifier
            .padding(
                top = 12.dp,
                bottom = 8.dp,
                end = 80.dp
            )
    )
}