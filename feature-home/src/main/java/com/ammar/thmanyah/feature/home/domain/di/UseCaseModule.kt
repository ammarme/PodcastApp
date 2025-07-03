package com.ammar.thmanyah.feature.home.domain.di

import com.ammar.thmanyah.core.data.repository.interfaces.HomeRepository
import com.ammar.thmanyah.feature.home.domain.usecase.GetHomeSectionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun productListUseCaseProvider(repository: HomeRepository): GetHomeSectionsUseCase {
        return GetHomeSectionsUseCase(repository)
    }
}