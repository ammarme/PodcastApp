package com.ammar.thmanyah.core.model

data class SectionDto(
    val name: String? = null,
    val type: String? = null,
    val contentType: String? = null,
    val order: Int? = null,
    val content: List<SectionContentDto>? = null
)