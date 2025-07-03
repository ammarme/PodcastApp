package com.ammar.thmanyah.core.data.mapper

import com.ammar.thmanyah.core.model.AudioArticle
import com.ammar.thmanyah.core.model.AudioBook
import com.ammar.thmanyah.core.model.Content
import com.ammar.thmanyah.core.model.Episode
import com.ammar.thmanyah.core.model.HomeSectionResponse
import com.ammar.thmanyah.core.model.HomeSectionsDto
import com.ammar.thmanyah.core.model.Podcast
import com.ammar.thmanyah.core.model.SectionContentDto
import com.ammar.thmanyah.core.model.SectionDto
import com.ammar.thmanyah.core.model.Sections
import javax.inject.Inject

class HomeSectionRemoteMapper @Inject constructor() {

    fun map(data: HomeSectionResponse): HomeSectionsDto =
        HomeSectionsDto(
            sections = mapSections(data.sections ?: emptyList()),
            totalPages = data.pagination?.totalPages
        )

    private fun mapSections(remoteSections: List<Sections>): List<SectionDto> =
        remoteSections.map {
            SectionDto(
                name = it.name,
                type = it.type,
                contentType = it.contentType,
                order = it.order?.toIntOrNull(),
                content = mapContentTypes(it.content ?: emptyList())
            )
        }

    private fun mapContentTypes(contentList: List<Content>): List<SectionContentDto> =
        contentList.map {
            when (it) {
                is AudioArticle -> SectionContentDto.AudioArticleDto(
                    articleId = it.articleId,
                    name = it.name,
                    authorName = it.authorName,
                    description = it.description,
                    avatarUrl = it.avatarUrl,
                    duration = it.duration,
                    releaseDate = it.releaseDate,
                    score = it.score
                )

                is AudioBook -> SectionContentDto.AudioBookDto(
                    audiobookId = it.audiobook_id,
                    name = it.name,
                    authorName = it.author_name,
                    description = it.description,
                    avatarUrl = it.avatar_url,
                    duration = it.duration,
                    language = it.language,
                    releaseDate = it.releaseDate,
                    score = it.score
                )

                is Episode -> SectionContentDto.EpisodeDto(
                    episodeId = it.episode_id,
                    name = it.name,
                    seasonNumber = it.season_number,
                    episodeType = it.episode_type,
                    podcastName = it.podcast_name,
                    authorName = it.author_name,
                    description = it.description,
                    duration = it.duration,
                    audioUrl = it.audio_url,
                    avatarUrl = it.avatar_url,
                    releaseDate = it.releaseDate,
                    podcastId = it.podcast_id,
                    score = it.score
                )

                is Podcast -> SectionContentDto.PodcastDto(
                    podcastId = it.podcastId,
                    name = it.name,
                    description = it.description,
                    avatarUrl = it.avatarUrl,
                    episodeCount = it.episodeCount,
                    duration = it.duration,
                    language = it.language,
                    priority = it.priority,
                    popularityScore = it.popularityScore,
                    score = it.score
                )
            }
        }

}