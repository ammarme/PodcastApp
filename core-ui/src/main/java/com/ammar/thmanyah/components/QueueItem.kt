package com.ammar.thmanyah.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ammar.thmanyah.core.model.ItemUiModel
import com.ammar.thmanyah.core.ui.theme.AppTheme

@Composable
fun QueueItem(
    modifier: Modifier = Modifier,
    item: ItemUiModel,
) {
    Card(
        modifier =
            modifier
                .padding(horizontal = AppTheme.spaces.spaceXs)
                .width(200.dp)
                .height(96.dp),
        shape = AppTheme.radius.radiusS,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            ImageLoader(
                modifier = Modifier.fillMaxSize(),
                url = item.imageUrl,
                cornerRadius = AppTheme.radius.radiusS,
            )

            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(
                            brush =
                                Brush.verticalGradient(
                                    colors =
                                        listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.7f),
                                        ),
                                    startY = 0f,
                                    endY = Float.POSITIVE_INFINITY,
                                ),
                        ),
            )

            Column(
                modifier =
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(AppTheme.spaces.spaceM)
                        .fillMaxWidth(),
            ) {
                Text(
                    text = item.title,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = item.subtitle,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}