package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.ui.theme.CardColor
import com.daniel.budgeplanner.utils.IMAGE

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovementButton(
    icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(width = 142.dp)
            .height(height = 65.dp),
        backgroundColor = CardColor,
        elevation = 4.dp,
        shape = RoundedCornerShape(size = 12.dp),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
         ){
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp),
                painter = painterResource(id = icon),
                contentDescription = IMAGE)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}