package com.ammar.thmanyah.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ammar.thmanyah.core.model.ItemUiModel
import com.ammar.thmanyah.core.ui.theme.AppTheme

@Composable
fun BigSquareItems(
    modifier: Modifier = Modifier,
    title: String,
    items: List<ItemUiModel>,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = AppTheme.spaces.spaceM),
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(horizontal = AppTheme.spaces.spaceL)
                .padding(bottom = AppTheme.spaces.spaceL),
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = AppTheme.spaces.spaceL),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spaces.spaceL),
        ) {
            items(items) { item ->
                BigSquareItem(
                    modifier = Modifier,
                    item = item,
                )
            }
        }
    }
}