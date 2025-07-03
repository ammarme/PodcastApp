package com.ammar.thmanyah.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ammar.thmanyah.core.ui.theme.AppTheme

@Composable
fun HomeLoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(bottom = AppTheme.spaces.spaceL)
    ) {

        LazyColumn(
            contentPadding = PaddingValues(
                AppTheme.spaces.space2Xs
            )
        ) {

            items(5) {
                LazyRow(
                    contentPadding = PaddingValues(AppTheme.spaces.spaceS),
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    items(4) {
                        ShimmerItem()
                    }
                }

            }

        }
    }
}