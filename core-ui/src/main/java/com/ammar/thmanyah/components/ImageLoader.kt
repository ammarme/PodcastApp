package com.ammar.thmanyah.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.ammar.thmanyah.core.ui.R
import com.ammar.thmanyah.core.ui.theme.AppTheme

@Composable
fun ImageLoader(
    modifier: Modifier,
    url: String,
    contentScale: ContentScale = ContentScale.Crop,
    cornerRadius: RoundedCornerShape = AppTheme.radius.radiusL,
) {
    AsyncImage(
        modifier = modifier.clip(cornerRadius),
        model = url,
        placeholder = painterResource(R.drawable.placeholder_image),
        error = painterResource(R.drawable.placeholder_image),
        contentScale = contentScale,
        contentDescription = null,
    )
}