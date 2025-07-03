package com.ammar.thmanyah.feature.search.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ammar.thmanyah.components.BigSquareItems
import com.ammar.thmanyah.components.ErrorFullScreen
import com.ammar.thmanyah.components.QueueSection
import com.ammar.thmanyah.components.SquarSection
import com.ammar.thmanyah.components.TwoLineGridsSection
import com.ammar.thmanyah.core.model.ItemType
import com.ammar.thmanyah.core.ui.theme.AppTheme
import com.ammar.thmanyah.feature.search.R
import com.ammar.thmanyah.feature.search.presentation.SearchViewModel
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val listState = rememberLazyListState()
    val state = viewModel.searchScreenState.value
    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(searchQuery.value.text) {
        delay(300)
        if (searchQuery.value.text.isNotBlank()) {
            viewModel.getSearchResult(searchQuery.value.text)
        }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .imePadding()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = AppTheme.spaces.spaceL,
                        vertical = AppTheme.spaces.spaceM
                    ),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = AppTheme.spaces.spaceL),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(24.dp)
                    )

                    TextField(
                        value = searchQuery.value,
                        onValueChange = { searchQuery.value = it },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.search_placeholder_enhanced),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                    alpha = 0.7f
                                )
                            )
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = AppTheme.spaces.spaceM),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.primary,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                        ),
                        textStyle = MaterialTheme.typography.bodyLarge,
                        singleLine = true
                    )

                    AnimatedVisibility(
                        visible = searchQuery.value.text.isNotEmpty(),
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        IconButton(
                            onClick = { searchQuery.value = TextFieldValue("") }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }

            when {
                searchQuery.value.text.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(AppTheme.spaces.space2Xl),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                        )

                        Spacer(modifier = Modifier.height(AppTheme.spaces.spaceL))

                        Text(
                            text = stringResource(R.string.search_empty_title),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.height(AppTheme.spaces.spaceS))

                        Text(
                            text = stringResource(R.string.search_empty_subtitle),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        )
                    }
                }

                state.error.isNotEmpty() -> {
                    ErrorFullScreen(
                        modifier = Modifier.fillMaxSize(),
                        retryAction = {
                            viewModel.refreshSearch(searchQuery.value.text)
                        },
                        title = stringResource(R.string.error_title),
                        message = state.error,
                    )
                }

                else -> {
                    LazyColumn(
                        state = listState,
                        contentPadding = PaddingValues(
                            horizontal = AppTheme.spaces.spaceL,
                            vertical = AppTheme.spaces.spaceM
                        ),
                        verticalArrangement = Arrangement.spacedBy(AppTheme.spaces.spaceM)
                    ) {
                        items(state.data ?: emptyList()) { item ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                ),
                                shape = AppTheme.radius.radiusL
                            ) {
                                when (item.type) {
                                    ItemType.Queue ->
                                        QueueSection(
                                            title = item.title,
                                            items = item.content,
                                        )

                                    ItemType.Square ->
                                        SquarSection(
                                            title = item.title,
                                            items = item.content,
                                        )

                                    ItemType.BigSquare ->
                                        BigSquareItems(
                                            title = item.title,
                                            items = item.content,
                                        )

                                    ItemType.TwoLinesGrid ->
                                        TwoLineGridsSection(
                                            title = item.title,
                                            items = item.content,
                                        )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}