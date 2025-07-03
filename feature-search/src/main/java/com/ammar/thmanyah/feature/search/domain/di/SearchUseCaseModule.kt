package com.ammar.thmanyah.feature.search.domain.di

import com.ammar.thmanyah.core.data.repository.HomeRepositoryImpl
import com.ammar.thmanyah.feature.search.domain.usecase.GetSearchResultUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SearchUseCaseModule {

    @Provides
    @Singleton
    fun provideGetSearchResultUseCase(repositoryImpl: HomeRepositoryImpl): GetSearchResultUseCase {
        return GetSearchResultUseCase(repositoryImpl)
    }
}