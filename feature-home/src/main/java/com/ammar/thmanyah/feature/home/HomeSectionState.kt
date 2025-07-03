package com.ammar.thmanyah.feature.home

import com.ammar.thmanyah.core.model.SectionUiModel

data class HomeSectionState(
    val isShimmerEnabled: Boolean = false,
    val isInitiated: Boolean = false,
    val isPaginationLoading: Boolean = false,
    val data: ArrayList<SectionUiModel>? = null,
    val currentPage: Int = 0,
    val totalPages: Int? = null,
    var error: String = ""
)