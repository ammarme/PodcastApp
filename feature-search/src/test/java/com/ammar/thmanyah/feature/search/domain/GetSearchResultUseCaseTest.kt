package com.ammar.thmanyah.feature.search.domain

import com.ammar.thmanyah.core.data.repository.HomeRepositoryImpl
import com.ammar.thmanyah.core.testing.TestDataBuilder
import com.ammar.thmanyah.feature.search.domain.usecase.GetSearchResultUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetSearchResultUseCaseTest {

    private var getSearchResultUseCase: GetSearchResultUseCase? = null
    private val repositoryImpl: HomeRepositoryImpl = mockk()

    @Before
    fun setup() {
        getSearchResultUseCase = GetSearchResultUseCase(
            repositoryImpl
        )
    }

    @Test
    fun `when search by any word then return HomeSections`() = runTest {
        val expected = TestDataBuilder.createHomeSectionsDto(
            sectionsCount = 1,
            totalPages = 10
        )

        every { repositoryImpl.searchSections("search key") } returns flowOf(expected)

        val actual = getSearchResultUseCase!!.invoke("search key").first()

        assertEquals(actual, expected)
    }

}