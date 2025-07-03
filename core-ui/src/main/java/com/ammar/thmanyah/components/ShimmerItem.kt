package com.ammar.thmanyah.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ammar.thmanyah.core.ui.theme.AppTheme


@Composable
fun ShimmerItem(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .width(AppTheme.spaces.space9Xl)
            .padding(horizontal = AppTheme.spaces.space2Xs)
    ) {
        Box(
            modifier = Modifier
                .size(AppTheme.spaces.space8Xl)
                .shimmerEffect()
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.spaces.spaceS)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .shimmerEffect()
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.spaces.space2Xs)
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .shimmerEffect()
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.spaces.space2Xs)
        )
    }
}
