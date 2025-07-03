package com.ammar.thmanyah.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ammar.thmanyah.components.BigSquareItems
import com.ammar.thmanyah.components.ErrorFullScreen
import com.ammar.thmanyah.components.HomeLoadingScreen
import com.ammar.thmanyah.components.QueueSection
import com.ammar.thmanyah.components.SquarSection
import com.ammar.thmanyah.components.TwoLineGridsSection
import com.ammar.thmanyah.core.model.ItemType
import com.ammar.thmanyah.core.ui.theme.AppTheme
import com.ammar.thmanyah.feature.home.HomeSectionViewModel
import com.ammar.thmanyah.feature.home.R

@Composable
fun HomeSectionScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeSectionViewModel = hiltViewModel(),
) {
    val listState = rememberLazyListState()
    val state = viewModel.homeSectionState.value

    if (!state.isInitiated) {
        viewModel.getHomeSections()
    }

    val shouldLoadMore = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index
            lastVisibleItemIndex == layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            viewModel.getHomeSections()
        }
    }

    when {
        state.isShimmerEnabled -> {
            HomeLoadingScreen()
        }

        state.error.isEmpty().not() -> {
            ErrorFullScreen(
                modifier = Modifier.fillMaxSize(),
                retryAction = {
                    viewModel.refreshHomeSections()
                },
                title = stringResource(R.string.error_title),
                message = state.error,
            )
        }

        else -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    LazyColumn(
                        state = listState,
                        contentPadding = PaddingValues(
                            top = AppTheme.spaces.spaceL,
                            bottom = AppTheme.spaces.space3Xl,
                        ),
                        verticalArrangement = Arrangement.spacedBy(
                            AppTheme.spaces.spaceM,
                        ),
                    ) {
                        items(state.data ?: emptyList()) { item ->
                            when (item.type) {
                                ItemType.Queue -> QueueSection(
                                    title = item.title,
                                    items = item.content,
                                )

                                ItemType.Square -> SquarSection(
                                    title = item.title,
                                    items = item.content,
                                )

                                ItemType.BigSquare -> BigSquareItems(
                                    title = item.title,
                                    items = item.content,
                                )

                                ItemType.TwoLinesGrid -> TwoLineGridsSection(
                                    title = item.title,
                                    items = item.content,
                                )
                            }
                        }
                    }
                }

                if (state.isPaginationLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .align(Alignment.BottomCenter)
                            .background(
                                MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp),
                            color = MaterialTheme.colorScheme.primary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }
                }
            }
        }
    }
}