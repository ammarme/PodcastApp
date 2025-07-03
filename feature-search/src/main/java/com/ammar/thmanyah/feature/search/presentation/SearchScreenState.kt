package com.ammar.thmanyah.feature.search.presentation

import com.ammar.thmanyah.core.model.SectionUiModel

data class SearchScreenState(
    val isLoading: Boolean = false,
    val data: List<SectionUiModel>? = null,
    var error: String = ""
)