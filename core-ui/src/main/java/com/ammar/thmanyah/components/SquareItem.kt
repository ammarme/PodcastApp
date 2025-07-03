package com.ammar.thmanyah.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ammar.thmanyah.core.model.ItemUiModel
import com.ammar.thmanyah.core.ui.theme.AppTheme

@Composable
fun SquareItem(
    modifier: Modifier = Modifier,
    podcastItem: ItemUiModel,
) {
    Column(
        modifier =
            modifier
                .width(130.dp)
                .padding(horizontal = AppTheme.spaces.spaceXs),
    ) {
        Card(
            shape = AppTheme.radius.radiusL,
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        ) {
            ImageLoader(
                modifier =
                    Modifier
                        .size(130.dp),
                url = podcastItem.imageUrl,
                cornerRadius = AppTheme.radius.radiusL,
            )
        }

        Spacer(modifier = Modifier.height(AppTheme.spaces.spaceS))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = podcastItem.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 18.sp,
        )

        Spacer(modifier = Modifier.height(AppTheme.spaces.spaceXs))

        Text(
            text = podcastItem.subtitle,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = androidx.compose.ui.graphics.Color.Gray,
        )
    }
}

@Preview
@Composable
fun PodcastItemPreview() {
    SquareItem(
        podcastItem =
            ItemUiModel(
                imageUrl = "https://media.npr.org/assets/img/2022/09/23/up-first_tile_npr-network-01_sq-cd1dc7e35846274fc57247cfcb9cd4dddbb2d635.jpg?s=1400&c=66&f=jpg",
                title = "Up First from NPR",
                subtitle = "11:50",
            ),
    )
}
