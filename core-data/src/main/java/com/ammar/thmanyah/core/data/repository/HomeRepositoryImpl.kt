package com.ammar.thmanyah.core.data.repository

import com.ammar.thmanyah.core.data.mapper.HomeSectionRemoteMapper
import com.ammar.thmanyah.core.data.repository.interfaces.HomeRepository
import com.ammar.thmanyah.core.data.source.remote.HomeRemoteDataSourceImpl
import com.ammar.thmanyah.core.model.HomeSectionsDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeSectionRemoteMapper: HomeSectionRemoteMapper,
    private val homeSectionDataSource: HomeRemoteDataSourceImpl
) : HomeRepository {
    override fun getHomeSections(pageNumber: Int): Flow<HomeSectionsDto> {
        return homeSectionDataSource.getHomeSections(pageNumber).map {
            homeSectionRemoteMapper.map(it)
        }
    }

    override fun searchSections(searchKey: String): Flow<HomeSectionsDto> {
        return homeSectionDataSource.search(searchKey).map {
            homeSectionRemoteMapper.map(it)
        }
    }
}