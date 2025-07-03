package com.ammar.thmanyah.core.data.source

import com.ammar.thmanyah.core.model.HomeSectionResponse
import kotlinx.coroutines.flow.Flow

interface HomeRemoteDateSource {
    fun getHomeSections(pageNumber: Int): Flow<HomeSectionResponse>
    fun search(searchKey: String): Flow<HomeSectionResponse>
}