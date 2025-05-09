package com.daniel.budgetplanner.ui.composables

import android.content.res.Configuration
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.daniel.budgetplanner.ui.theme.OnboardingBackground
import kotlinx.coroutines.delay

@Composable
fun LoadingAnimation() {

    Column(
        modifier = Modifier
            .background(OnboardingBackground)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            LoadingAnimation3()
        }
    }
}

@Composable
fun LoadingAnimation3(
    circleColor: Color = Color.Green,
    circleSize: Dp = 36.dp,
    animationDelay: Int = 400,
    initialAlpha: Float = 0.3f
) {
    val circles = listOf(
        remember {
            Animatable(initialValue = initialAlpha)
        },
        remember {
            Animatable(initialValue = initialAlpha)
        },
        remember {
            Animatable(initialValue = initialAlpha)
        }
    )

    circles.forEachIndexed { index, animatable ->

        LaunchedEffect(Unit) {

            delay(timeMillis = (animationDelay / circles.size).toLong() * index)

            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDelay
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        circles.forEachIndexed { index, animatable ->

            if (index != 0) {
                Spacer(modifier = Modifier.width(width = 6.dp))
            }

            Box(
                modifier = Modifier
                    .size(size = circleSize)
                    .clip(shape = CircleShape)
                    .background(
                        color = circleColor
                            .copy(alpha = animatable.value)
                    )
            ) { }
        }
    }
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE
)
@Composable
fun LoadingViewPreview(){
    LoadingAnimation()
}