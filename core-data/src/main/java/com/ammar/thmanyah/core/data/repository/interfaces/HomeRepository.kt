package com.ammar.thmanyah.core.data.repository.interfaces

import com.ammar.thmanyah.core.model.HomeSectionsDto
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getHomeSections(pageNumber: Int): Flow<HomeSectionsDto>

    fun searchSections(searchKey: String): Flow<HomeSectionsDto>

}