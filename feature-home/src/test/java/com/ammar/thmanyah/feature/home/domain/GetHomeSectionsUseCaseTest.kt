package com.ammar.thmanyah.feature.home.domain

import com.ammar.thmanyah.core.data.repository.HomeRepositoryImpl
import com.ammar.thmanyah.core.testing.TestDataBuilder
import com.ammar.thmanyah.feature.home.domain.usecase.GetHomeSectionsUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetHomeSectionsUseCaseTest {

    private var getHomeSectionsUseCase: GetHomeSectionsUseCase? = null
    private val repositoryImpl: HomeRepositoryImpl = mockk()

    @Before
    fun setup() {
        getHomeSectionsUseCase = GetHomeSectionsUseCase(
            repositoryImpl
        )
    }

    @Test
    fun `when getHomeSections then return HomeSections`() = runTest {
        val pageNumber = 1
        val expected = TestDataBuilder.createHomeSectionsDto(
            sectionsCount = 1,
            totalPages = 10
        )

        every { repositoryImpl.getHomeSections(pageNumber) } returns flowOf(expected)

        val actual = getHomeSectionsUseCase!!.invoke(pageNumber).first()

        assertEquals(actual, expected)
    }
}