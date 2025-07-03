package com.ammar.thmanyah.core.testing

import com.ammar.thmanyah.core.model.AudioArticle
import com.ammar.thmanyah.core.model.AudioBook
import com.ammar.thmanyah.core.model.Episode
import com.ammar.thmanyah.core.model.HomeSectionResponse
import com.ammar.thmanyah.core.model.HomeSectionsDto
import com.ammar.thmanyah.core.model.ItemType
import com.ammar.thmanyah.core.model.ItemUiModel
import com.ammar.thmanyah.core.model.Pagination
import com.ammar.thmanyah.core.model.Podcast
import com.ammar.thmanyah.core.model.SectionContentDto
import com.ammar.thmanyah.core.model.SectionDto
import com.ammar.thmanyah.core.model.SectionUiModel
import com.ammar.thmanyah.core.model.Sections

object TestDataBuilder {

    fun createHomeSectionsDto(
        sectionsCount: Int = 1,
        totalPages: Int = 10
    ): HomeSectionsDto {
        return HomeSectionsDto(
            sections = (1..sectionsCount).map { index ->
                createSectionDto(order = index)
            },
            totalPages = totalPages
        )
    }

    fun createSectionDto(
        name: String = "Test Section",
        type: String = "square",
        contentType: String = "podcast",
        order: Int = 1,
        contentCount: Int = 1
    ): SectionDto {
        return SectionDto(
            name = name,
            type = type,
            contentType = contentType,
            order = order,
            content = (1..contentCount).map { index ->
                createSectionContentDto(id = index.toString())
            }
        )
    }

    fun createSectionContentDto(
        id: String = "1",
        name: String = "Test Content",
        description: String = "Test Description",
        avatarUrl: String = "https://test.com/avatar.jpg"
    ): SectionContentDto.PodcastDto {
        return SectionContentDto.PodcastDto(
            podcastId = id,
            name = name,
            description = description,
            avatarUrl = avatarUrl,
            episodeCount = 10,
            duration = 1000,
            language = "ar",
            priority = "1",
            popularityScore = "220",
            score = "22323"
        )
    }

    fun createHomeSectionResponse(
        sectionsCount: Int = 1,
        totalPages: Int = 10
    ): HomeSectionResponse {
        return HomeSectionResponse(
            sections = (1..sectionsCount).map { index ->
                createSections(order = index.toString())
            },
            pagination = Pagination(
                nextPage = "2",
                totalPages = totalPages
            )
        )
    }

    fun createSections(
        name: String = "Test Section",
        type: String = "square",
        contentType: String = "podcast",
        order: String = "1",
        contentCount: Int = 1
    ): Sections {
        return Sections(
            name = name,
            type = type,
            contentType = contentType,
            order = order,
            content = (1..contentCount).map { index ->
                createPodcast(id = index.toString())
            }
        )
    }

    fun createPodcast(
        id: String = "1",
        name: String = "Test Podcast",
        description: String = "Test Description",
        avatarUrl: String = "https://test.com/avatar.jpg"
    ): Podcast {
        return Podcast(
            podcastId = id,
            name = name,
            description = description,
            avatarUrl = avatarUrl,
            episodeCount = 10,
            duration = 1000,
            language = "ar",
            priority = "1",
            popularityScore = "220",
            score = "22323"
        )
    }

    fun createSectionUiModel(
        title: String = "Test Section",
        order: Int = 1,
        type: ItemType = ItemType.Square,
        itemsCount: Int = 1
    ): SectionUiModel {
        return SectionUiModel(
            title = title,
            content = (1..itemsCount).map { index ->
                createItemUiModel(title = "Test Item $index")
            },
            order = order,
            type = type
        )
    }

    fun createItemUiModel(
        imageUrl: String = "https://test.com/image.jpg",
        title: String = "Test Item",
        subtitle: String = "Test Subtitle"
    ): ItemUiModel {
        return ItemUiModel(
            imageUrl = imageUrl,
            title = title,
            subtitle = subtitle
        )
    }

    fun createEpisode(
        episode_id: String = "1",
        title: String = "Test Episode",
        description: String = "Test Description"
    ): Episode {
        return Episode(
            episode_id = episode_id,
            name = title,
            season_number = 1,
            episode_type = "full",
            podcast_name = "Test Podcast",
            author_name = "Test Author",
            description = description,
            duration = 1800,
            audio_url = "https://test.com/audio.mp3",
            avatar_url = "https://test.com/image.jpg",
            releaseDate = "2023-01-01T00:00:00Z",
            podcast_id = "1",
            score = 4.5
        )
    }

    fun createAudioArticle(
        articleId: String = "1",
        title: String = "Test Article",
        description: String = "Test Description"
    ): AudioArticle {
        return AudioArticle(
            articleId = articleId,
            name = title,
            authorName = "Test Author",
            description = description,
            avatarUrl = "https://test.com/image.jpg",
            duration = 600,
            releaseDate = "2023-01-01T00:00:00Z",
            score = 5
        )
    }

    fun createAudioBook(
        bookId: String = "1",
        title: String = "Test Book",
        description: String = "Test Description"
    ): AudioBook {
        return AudioBook(
            audiobook_id = bookId,
            name = title,
            author_name = "Test Author",
            description = description,
            avatar_url = "https://test.com/cover.jpg",
            duration = 36000,
            language = "ar",
            releaseDate = "2023-01-01T00:00:00Z",
            score = 4
        )
    }
}