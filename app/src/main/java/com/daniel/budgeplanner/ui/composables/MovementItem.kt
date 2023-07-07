package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.data.Category
import com.daniel.budgeplanner.data.MovementItem
import com.daniel.budgeplanner.ui.theme.BudgetGreen
import com.daniel.budgeplanner.ui.theme.CardColor
import com.daniel.budgeplanner.ui.theme.ExpensesColor

@Composable
fun MovementItem(
    item: MovementItem
) {
    val resourceId: Int = when (item.category) {
        Category.FOOD_EXPENSES -> R.drawable.shopping_cart
        Category.ANT_EXPENSES -> R.drawable.money
        Category.SERVICES_EXPENSES -> R.drawable.services
        Category.MONTHLY_INCOMES -> R.drawable.finance_icon
        Category.OTHER_INCOMES -> R.drawable.various_input
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(
                horizontal = 12.dp,
                vertical = 4.dp
            ),
        elevation = 4.dp,
        backgroundColor = CardColor
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = "icon",
                modifier = Modifier
                    .weight(1F)
                    .height(50.dp)
                    .width(50.dp)
                    .padding(4.dp)
            )

            Column(
                modifier = Modifier
                    .weight(2F),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = item.name,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = item.category.gasto,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                Text(
                    text = item.date,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding( top = 6.dp)
                )
            }

            Column(modifier = Modifier.weight(3F)) {
                AmountTextView(item)
            }
        }
    }
}

@Composable
fun AmountTextView(
    item: MovementItem
) {
    val textColor = when (item.category) {
        Category.FOOD_EXPENSES,
        Category.ANT_EXPENSES,
        Category.SERVICES_EXPENSES -> ExpensesColor
        else -> BudgetGreen
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.item_amount, item.amount),
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            ),
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}