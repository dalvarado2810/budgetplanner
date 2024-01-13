package com.daniel.budgeplanner.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.daniel.budgeplanner.utils.IMAGE

@Composable
fun ImageSteps(
    image: Int,
    pt: Dp,
    pb: Dp,
    ps: Dp,
    width: Dp,
    height: Dp
    ) {
    Image(
        painter = painterResource(id = image),
        contentDescription = IMAGE,
        modifier = Modifier
            .padding(top = pt, start = ps, bottom = pb)
            .width(width = width)
            .height(height = height)
    )
}