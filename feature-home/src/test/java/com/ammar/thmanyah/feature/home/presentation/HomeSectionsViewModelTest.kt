package com.ammar.thmanyah.feature.home.presentation

import com.ammar.thmanyah.core.data.mapper.MapperSectionUiModel
import com.ammar.thmanyah.core.testing.TestDataBuilder
import com.ammar.thmanyah.feature.home.HomeSectionViewModel
import com.ammar.thmanyah.feature.home.domain.usecase.GetHomeSectionsUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class HomeSectionsViewModelTest {

    private var searchViewModel: HomeSectionViewModel? = null
    val getHomeSectionsUseCase: GetHomeSectionsUseCase = mockk()
    private val mapperSectionUiModel: MapperSectionUiModel = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        searchViewModel = HomeSectionViewModel(
            getHomeSectionsUseCase = getHomeSectionsUseCase,
            mapperSectionUiModel = mapperSectionUiModel,
            ioDispatcher = testDispatcher
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when getHomeSections then update state with mapped data`() = runTest(testDispatcher) {
        val response = TestDataBuilder.createHomeSectionsDto(
            sectionsCount = 1, totalPages = 10
        )

        val expectedSectionUiModel = TestDataBuilder.createSectionUiModel(
            title = "Test Section", order = 1, itemsCount = 1
        )

        every { getHomeSectionsUseCase.invoke(1) } returns flowOf(response)
        every {
            mapperSectionUiModel.map(response.sections!![0])
        } returns expectedSectionUiModel

        val initialState = searchViewModel!!.homeSectionState.value
        assertNull("Initial data should be null", initialState.data)
        assertEquals("Initial current page should be 0", 0, initialState.currentPage)
        assertFalse("Initial isInitiated should be false", initialState.isInitiated)

        searchViewModel!!.getHomeSections()

        testDispatcher.scheduler.advanceUntilIdle()

        val finalState = searchViewModel!!.homeSectionState.value
        assertTrue("Final isInitiated should be true", finalState.isInitiated)
        assertFalse("Final isShimmerEnabled should be false", finalState.isShimmerEnabled)
        assertFalse("Final isPaginationLoading should be false", finalState.isPaginationLoading)
        assertEquals("Final data size should be 1", 1, finalState.data?.size)
        assertEquals(
            "Final data should match expected",
            expectedSectionUiModel,
            finalState.data?.get(0)
        )
        assertEquals("Final current page should be 1", 1, finalState.currentPage)
        assertEquals("Final total pages should be 10", 10, finalState.totalPages)

        verify(exactly = 1) { getHomeSectionsUseCase(1) }
        verify(exactly = 1) { mapperSectionUiModel.map(response.sections!![0]) }
    }
}