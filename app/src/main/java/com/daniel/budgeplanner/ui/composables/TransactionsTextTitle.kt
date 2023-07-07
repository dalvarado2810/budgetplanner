package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.utils.ICON

@Composable
fun TransactionsTextTitle(
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)
    ) {
        Text(
            text = stringResource(id = R.string.last_transactions),
            color = Color.Black,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(8.dp)
                .weight(3F)
        )
        Image(
            painter = painterResource(id = R.drawable.filter_icon),
            contentDescription = ICON,
            modifier = Modifier
                .weight(1F)
                .padding(start = 12.dp),
        )
    }
}