package com.ammar.thmanyah.core.data.mapper

import com.ammar.thmanyah.core.model.ItemType
import com.ammar.thmanyah.core.model.ItemUiModel
import com.ammar.thmanyah.core.model.SectionContentDto
import com.ammar.thmanyah.core.model.SectionDto
import com.ammar.thmanyah.core.model.SectionUiModel
import com.ammar.thmanyah.core.ui.extensions.fromMinsToHoursMinFormat
import javax.inject.Inject

class MapperSectionUiModel @Inject constructor() {

    fun map(section: SectionDto): SectionUiModel =
        SectionUiModel(
            title = section.name.orEmpty(),
            content = section.content?.map {
                mapContentToUiModel(it)
            } ?: emptyList(),
            order = section.order ?: 0,
            type = ItemType.fromString(section.type.orEmpty()) ?: ItemType.Square
        )

    private fun mapContentToUiModel(content: SectionContentDto): ItemUiModel {
        return when (content) {
            is SectionContentDto.AudioArticleDto -> ItemUiModel(
                imageUrl = content.avatarUrl.orEmpty(),
                title = content.name.orEmpty(),
                subtitle = content.authorName.orEmpty()
            )

            is SectionContentDto.AudioBookDto -> ItemUiModel(
                imageUrl = content.avatarUrl.orEmpty(),
                title = content.name.orEmpty(),
                subtitle = content.authorName.orEmpty()
            )

            is SectionContentDto.EpisodeDto -> ItemUiModel(
                imageUrl = content.avatarUrl.orEmpty(),
                title = content.name.orEmpty(),
                subtitle = content.duration?.fromMinsToHoursMinFormat().orEmpty()
            )

            is SectionContentDto.PodcastDto -> ItemUiModel(
                imageUrl = content.avatarUrl.orEmpty(),
                title = content.name.orEmpty(),
                subtitle = content.episodeCount.toString() + " " + "episodes"
            )
        }
    }
}