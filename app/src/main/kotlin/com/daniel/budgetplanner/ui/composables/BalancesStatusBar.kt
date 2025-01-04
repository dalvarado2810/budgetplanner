package com.daniel.budgetplanner.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgetplanner.R
import com.daniel.budgetplanner.ui.setActualBalanceColor
import com.daniel.budgetplanner.ui.theme.OnboardingBackground
import com.daniel.budgetplanner.ui.theme.fonts
import com.daniel.budgetplanner.ui.toFormattedAmount

@Composable
fun BalancesStatusBar (
    monthlyBalance: Int,
    otherBalance: Int,
    foodBalance: Int,
    healthBalance: Int,
    servicesBalance: Int,
    transportBalance: Int,
    outfitBalance: Int,
    antBalance: Int
) {

    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .padding(top = 4.dp, start = 4.dp)
                    .width(165.dp)
                    .height(57.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = OnboardingBackground,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 4.dp, bottom = 6.dp),
                        text = stringResource(id = R.string.monthly_income),
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        modifier = Modifier,
                        text = toFormattedAmount(monthlyBalance),
                        color = setActualBalanceColor(monthlyBalance),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fonts
                        ),
                    )
                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 4.dp, start = 6.dp)
                    .width(165.dp)
                    .height(57.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = OnboardingBackground,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 4.dp, bottom = 6.dp),
                        text = stringResource(id = R.string.other_income),
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        modifier = Modifier,
                        text = toFormattedAmount(otherBalance),
                        color = setActualBalanceColor(otherBalance),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fonts
                        ),
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .padding(top = 4.dp, start = 4.dp)
                    .width(110.dp)
                    .height(47.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = OnboardingBackground,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 4.dp, bottom = 2.dp),
                        text = stringResource(id = R.string.food_balance),
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        modifier = Modifier,
                        text = toFormattedAmount(foodBalance),
                        color = setActualBalanceColor(foodBalance),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fonts
                        ),
                    )
                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 4.dp, start = 4.dp)
                    .width(110.dp)
                    .height(47.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = OnboardingBackground,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 4.dp, bottom = 2.dp),
                        text = stringResource(id = R.string.health_expenses),
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        modifier = Modifier,
                        text = toFormattedAmount(healthBalance),
                        color = setActualBalanceColor(healthBalance),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fonts
                        ),
                    )
                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 4.dp, start = 4.dp)
                    .width(110.dp)
                    .height(47.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = OnboardingBackground,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 4.dp, bottom = 2.dp),
                        text = stringResource(id = R.string.service_expenses),
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        modifier = Modifier,
                        text = toFormattedAmount(servicesBalance),
                        color = setActualBalanceColor(servicesBalance),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fonts
                        ),
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .padding(top = 4.dp, start = 4.dp)
                    .width(110.dp)
                    .height(47.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = OnboardingBackground,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 4.dp, bottom = 2.dp),
                        text = stringResource(id = R.string.transport_expenses),
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        modifier = Modifier,
                        text = toFormattedAmount(transportBalance),
                        color = setActualBalanceColor(transportBalance),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fonts
                        ),
                    )
                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 4.dp, start = 4.dp)
                    .width(110.dp)
                    .height(47.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = OnboardingBackground,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 4.dp, bottom = 2.dp),
                        text = stringResource(id = R.string.outfit_expenses),
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        modifier = Modifier,
                        text = toFormattedAmount(outfitBalance),
                        color = setActualBalanceColor(outfitBalance),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fonts
                        ),
                    )
                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 4.dp, start = 4.dp)
                    .width(110.dp)
                    .height(47.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = OnboardingBackground,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 4.dp, bottom = 2.dp),
                        text = stringResource(id = R.string.ant_expenses),
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        modifier = Modifier,
                        text = toFormattedAmount(antBalance),
                        color = setActualBalanceColor(antBalance),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fonts
                        ),
                    )
                }
            }
        }
    }
}