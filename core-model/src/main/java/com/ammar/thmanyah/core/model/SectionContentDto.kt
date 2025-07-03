package com.ammar.thmanyah.core.model

sealed class SectionContentDto {
    data class AudioArticleDto(
        val articleId: String?,
        val name: String?,
        val authorName: String?,
        val description: String?,
        val avatarUrl: String?,
        val duration: Int?,
        val releaseDate: String?,
        val score: Int?
    ) : SectionContentDto()

    data class EpisodeDto(
        val episodeId: String?,
        val name: String?,
        val seasonNumber: Int?,
        val episodeType: String?,
        val podcastName: String?,
        val authorName: String?,
        val description: String?,
        val duration: Int?,
        val audioUrl: String?,
        val avatarUrl: String?,
        val releaseDate: String?,
        val podcastId: String?,
        val score: Double?
    ) : SectionContentDto()

    data class AudioBookDto(
        val audiobookId: String?,
        val name: String?,
        val authorName: String?,
        val description: String?,
        val avatarUrl: String?,
        val duration: Int?,
        val language: String?,
        val releaseDate: String?,
        val score: Int?
    ) : SectionContentDto()

    data class PodcastDto(
        val podcastId: String? = null,
        val name: String? = null,
        val description: String? = null,
        val avatarUrl: String? = null,
        val episodeCount: Int? = null,
        val duration: Int? = null,
        val language: String? = null,
        val priority: String? = null,
        val popularityScore: String? = null,
        val score: String? = null

    ) : SectionContentDto()
}