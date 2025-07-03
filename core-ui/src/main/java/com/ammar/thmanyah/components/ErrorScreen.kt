package com.ammar.thmanyah.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ammar.thmanyah.core.ui.theme.AppTheme

@Composable
fun ErrorFullScreen(
    modifier: Modifier = Modifier,
    title: String,
    message: String? = null,
    retry: String = "Retry",
    retryAction: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .padding(AppTheme.spaces.spaceL)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally),
            tint = MaterialTheme.colorScheme.error
        )

        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.spaces.spaceXs)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        message?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = AppTheme.spaces.spaceXs)
                    .align(Alignment.CenterHorizontally),
                text = it,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        retryAction?.let {
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                content = { Text(text = retry) },
                onClick = {
                    retryAction()
                })
        }
    }
}

@Preview
@Composable
fun ErrorFullScreenPreview() {
    ErrorFullScreen(
        title = "Something went wrong",
        retryAction = {}
    )

}