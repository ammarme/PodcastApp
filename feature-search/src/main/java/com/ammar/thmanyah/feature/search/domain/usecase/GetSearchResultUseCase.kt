package com.ammar.thmanyah.feature.search.domain.usecase

import com.ammar.thmanyah.core.data.repository.HomeRepositoryImpl
import com.ammar.thmanyah.core.model.HomeSectionsDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val repositoryImpl: HomeRepositoryImpl
) {
    operator fun invoke(searchKey: String): Flow<HomeSectionsDto> {
        return repositoryImpl.searchSections(searchKey)
    }

}