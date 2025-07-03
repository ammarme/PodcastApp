package com.ammar.thmanyah.feature.search.presentation

import com.ammar.thmanyah.core.data.mapper.MapperSectionUiModel
import com.ammar.thmanyah.core.model.HomeSectionsDto
import com.ammar.thmanyah.core.model.ItemType
import com.ammar.thmanyah.core.model.ItemUiModel
import com.ammar.thmanyah.core.model.SectionContentDto
import com.ammar.thmanyah.core.model.SectionDto
import com.ammar.thmanyah.core.model.SectionUiModel
import com.ammar.thmanyah.core.ui.extensions.fromMinsToHoursMinFormat
import com.ammar.thmanyah.feature.search.domain.usecase.GetSearchResultUseCase
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SearchViewModelTest {

    private var searchViewModel: SearchViewModel? = null
    val getSearchResultUseCase: GetSearchResultUseCase = mockk()
    private val mapperSectionUiModel: MapperSectionUiModel = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        searchViewModel = SearchViewModel(
            getSearchResultUseCase = getSearchResultUseCase,
            mapperSectionUiModel = mapperSectionUiModel,
            ioDispatcher = testDispatcher
        )
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when search by any word then return HomeSections`() = runTest {
        val searchKey = "key"

        val response = HomeSectionsDto(
            sections = listOf(
                SectionDto(
                    name = "section 1",
                    type = "square",
                    contentType = "podcast",
                    order = 1,
                    content = listOf(
                        SectionContentDto.PodcastDto(
                            podcastId = "1",
                            name = "podcast",
                            description = "descripton",
                            avatarUrl = "url",
                            episodeCount = 10,
                            duration = 1000,
                            language = "ar",
                            priority = "1",
                            popularityScore = "220",
                            score = "22323"
                        )
                    )
                )
            ),
            totalPages = 10
        )
        val expected = SectionUiModel(
            title = "section 1",
            content = listOf(
                ItemUiModel(
                    imageUrl = "url",
                    title = "podcast",
                    subtitle = 1000.fromMinsToHoursMinFormat()
                )
            ),
            order = 1,
            type = ItemType.Square
        )

        every { getSearchResultUseCase.invoke(searchKey) } returns flowOf(
            response
        )
        every {
            mapperSectionUiModel.map(
                response.sections!![0]
            )
        } returns expected

        searchViewModel!!.getSearchResult(searchKey)

        coVerify(exactly = 1) { getSearchResultUseCase(any()) }
    }
}