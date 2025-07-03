package com.ammar.thmanyah.core.data.mapper

import com.ammar.thmanyah.core.testing.TestDataBuilder
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeSectionRemoteMapperTest {

    private var homeSectionRemoteMapper = HomeSectionRemoteMapper()

    @Test
    fun `map HomeSectionResponse to HomeSectionsDto`() {
        val homeSectionResponse = TestDataBuilder.createHomeSectionResponse(
            sectionsCount = 1,
            totalPages = 10
        )

        val result = homeSectionRemoteMapper.map(homeSectionResponse)

        assertEquals(1, result.sections?.size)
        assertEquals("Test Section", result.sections?.get(0)?.name)
        assertEquals("square", result.sections?.get(0)?.type)
        assertEquals("podcast", result.sections?.get(0)?.contentType)
        assertEquals(1, result.sections?.get(0)?.order) // String "1" should be converted to Int 1
        assertEquals(1, result.sections?.get(0)?.content?.size)
        assertEquals(10, result.totalPages)
    }
}