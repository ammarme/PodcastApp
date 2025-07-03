package com.ammar.thmanyah.core.data.repository

import com.ammar.thmanyah.core.data.mapper.HomeSectionRemoteMapper
import com.ammar.thmanyah.core.data.repository.interfaces.HomeRepository
import com.ammar.thmanyah.core.data.source.remote.HomeRemoteDataSourceImpl
import com.ammar.thmanyah.core.testing.TestDataBuilder
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeRepositoryTest {
    private var repository: HomeRepository? = null

    private val homeSectionRemoteMapper: HomeSectionRemoteMapper = mockk()
    private val homeSectionDataSource: HomeRemoteDataSourceImpl = mockk()

    @Before
    fun setup() {
        repository = HomeRepositoryImpl(
            homeSectionRemoteMapper,
            homeSectionDataSource,
        )
    }

    @Test
    fun `when getHomeSections then return HomeSections`() = runTest {
        val pageNumber = 1
        val expected = TestDataBuilder.createHomeSectionsDto(
            sectionsCount = 1,
            totalPages = 10
        )
        val homeSectionResponse = TestDataBuilder.createHomeSectionResponse(
            sectionsCount = 1,
            totalPages = 10
        )

        every { homeSectionRemoteMapper.map(homeSectionResponse) } returns expected
        every { homeSectionDataSource.getHomeSections(pageNumber) } returns flowOf(
            homeSectionResponse
        )

        val actual = repository!!.getHomeSections(pageNumber).first()

        assertEquals(actual, expected)
    }

    @Test
    fun `when searchSections then return HomeSections`() = runTest {
        val searchKey = "Search fake"
        val expected = TestDataBuilder.createHomeSectionsDto(
            sectionsCount = 1,
            totalPages = 10
        )
        val homeSectionResponse = TestDataBuilder.createHomeSectionResponse(
            sectionsCount = 1,
            totalPages = 10
        )

        every { homeSectionRemoteMapper.map(homeSectionResponse) } returns expected
        every { homeSectionDataSource.search(searchKey) } returns flowOf(homeSectionResponse)

        val actual = repository!!.searchSections(searchKey).first()

        assertEquals(actual, expected)
    }
}