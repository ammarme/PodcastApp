package com.ammar.thmanyah.core.data.datasource

import com.ammar.thmanyah.core.data.source.HomeRemoteDateSource
import com.ammar.thmanyah.core.data.source.remote.HomeRemoteDataSourceImpl
import com.ammar.thmanyah.core.network.ApiService
import com.ammar.thmanyah.core.testing.TestDataBuilder
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeRemoteDataSourceTest {
    var homeRemoteDateSource: HomeRemoteDateSource? = null
    private val apiService: ApiService = mockk()

    @Before
    fun setup() {
        homeRemoteDateSource = HomeRemoteDataSourceImpl(
            apiService
        )
    }

    @Test
    fun `when getHomeSections then return HomeSections`() = runTest {
        val homeSectionResponse = TestDataBuilder.createHomeSectionResponse(
            sectionsCount = 1,
            totalPages = 10
        )

        coEvery { apiService.getHomeSections(1) } returns homeSectionResponse

        val actual = apiService.getHomeSections(1)

        assertEquals(actual, homeSectionResponse)
    }

    @Test
    fun `when search then return HomeSections`() = runTest {
        val homeSectionResponse = TestDataBuilder.createHomeSectionResponse(
            sectionsCount = 1,
            totalPages = 10
        )

        coEvery { apiService.search("search key") } returns homeSectionResponse

        val actual = apiService.search("search key")

        assertEquals(actual, homeSectionResponse)
    }
}