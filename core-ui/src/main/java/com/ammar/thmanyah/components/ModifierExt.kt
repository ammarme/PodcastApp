package com.ammar.thmanyah.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.ammar.thmanyah.core.ui.theme.ShimmerEffectGradiantCenterColor
import com.ammar.thmanyah.core.ui.theme.ShimmerEffectGradiantEndColor
import com.ammar.thmanyah.core.ui.theme.ShimmerEffectGradiantStartColor

private const val ANIMATION_DURATION: Long = 2500

@Suppress("ktlint:compose:modifier-composed-check")
fun Modifier.shimmerEffect(): Modifier =
    composed {
        var size by remember { mutableStateOf(IntSize.Zero) }
        val transition =
            rememberInfiniteTransition(
                label = "shimmer ",
            )

        val startOffsetX by transition.animateFloat(
            initialValue = -2 * size.width.toFloat(),
            targetValue = 2 * size.width.toFloat(),
            animationSpec = infiniteRepeatable(animation = tween(ANIMATION_DURATION.toInt())),
            label = "offest",
        )

        background(
            brush =
                Brush.linearGradient(
                    colors =
                        listOf(
                            ShimmerEffectGradiantStartColor,
                            ShimmerEffectGradiantCenterColor,
                            ShimmerEffectGradiantEndColor,
                        ),
                    start = Offset(startOffsetX, 0f),
                    end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat()),
                ),
        ).onGloballyPositioned {
            size = it.size
        }
    }