package com.ammar.thmanyah.feature.home.domain.usecase

import com.ammar.thmanyah.core.data.repository.interfaces.HomeRepository
import com.ammar.thmanyah.core.model.HomeSectionsDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeSectionsUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(pageNumber: Int): Flow<HomeSectionsDto> {
        return repository.getHomeSections(pageNumber)
    }

}