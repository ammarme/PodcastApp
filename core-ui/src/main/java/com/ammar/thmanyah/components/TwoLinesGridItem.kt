package com.ammar.thmanyah.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ammar.thmanyah.core.model.ItemUiModel
import com.ammar.thmanyah.core.ui.theme.AppTheme

@Composable
fun TwoLinesGridItem(
    modifier: Modifier = Modifier,
    item: ItemUiModel,
) {
    Card(
        modifier =
            modifier
                .width(280.dp)
                .padding(AppTheme.spaces.spaceXs),
        shape = AppTheme.radius.radiusL,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.spaces.spaceM),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ImageLoader(
                modifier = Modifier.size(64.dp),
                url = item.imageUrl,
                cornerRadius = AppTheme.radius.radiusS,
            )

            Spacer(modifier = Modifier.width(AppTheme.spaces.spaceM))

            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp,
                )

                Spacer(modifier = Modifier.height(AppTheme.spaces.spaceXs))

                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray,
                )
            }
        }
    }
}