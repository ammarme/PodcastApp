package com.ammar.thmanyah.core.data.source.remote

import com.ammar.thmanyah.core.data.source.HomeRemoteDateSource
import com.ammar.thmanyah.core.model.HomeSectionResponse
import com.ammar.thmanyah.core.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRemoteDataSourceImpl
@Inject constructor(
    private val apiService: ApiService
) : HomeRemoteDateSource {
    override fun getHomeSections(pageNumber: Int): Flow<HomeSectionResponse> = flow {
        emit(apiService.getHomeSections(pageNumber))
    }

    override fun search(searchKey: String): Flow<HomeSectionResponse> = flow {
        emit(apiService.search(searchKey))
    }

}