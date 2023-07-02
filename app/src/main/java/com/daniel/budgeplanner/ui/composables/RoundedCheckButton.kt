package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.budgeplanner.data.Category
import com.daniel.budgeplanner.data.CheckButton
import com.daniel.budgeplanner.ui.theme.BudgetGreen
import com.daniel.budgeplanner.utils.toCategoryItem

@Composable
fun RoundedCheckButton(
    item: String,
    categoryCheck: (item: Category) -> Unit
) {
    val isChecked = remember { mutableStateOf(false) }
    val circleSize = remember { mutableStateOf(20.dp) }
    val circleThickness = remember { mutableStateOf(2.dp) }
    val color = remember { mutableStateOf(Color.Gray) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .toggleable(value = isChecked.value,role = Role.Checkbox) {
                isChecked.value = it

                if (isChecked.value) {
                    color.value = BudgetGreen
                } else {
                    color.value = Color.Gray
                }
            }) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(circleSize.value)
                .background(color.value)
                .padding(circleThickness.value)
                .clip(CircleShape)
                .background(Color.White)  ,
            contentAlignment = Alignment.Center
        ) {
            if(isChecked.value){
                categoryCheck(item.toCategoryItem())
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                    tint = BudgetGreen
                )
            }
        }

        Text(
            text = item,
            color = color.value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}