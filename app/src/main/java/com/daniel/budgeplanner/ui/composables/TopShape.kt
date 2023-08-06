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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.R
import com.daniel.budgeplanner.ui.theme.BackGround
import com.daniel.budgeplanner.ui.theme.BudgetGreen
import com.daniel.budgeplanner.utils.EMPTY_STRING
import com.daniel.budgeplanner.utils.ICON
import com.daniel.budgeplanner.utils.IMAGE

@Composable
fun TopShape(
    title: String = EMPTY_STRING,
    buttonVisible: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackGround)
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = IMAGE,
            modifier = Modifier
                .width(width = 218.dp)
                .height(height = 172.dp)
        )
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