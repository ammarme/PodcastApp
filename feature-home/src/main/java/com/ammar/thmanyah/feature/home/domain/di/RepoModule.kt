package com.ammar.thmanyah.feature.home.domain.di

import com.ammar.thmanyah.core.data.repository.HomeRepositoryImpl
import com.ammar.thmanyah.core.data.repository.interfaces.HomeRepository
import com.ammar.thmanyah.core.data.source.HomeRemoteDateSource
import com.ammar.thmanyah.core.data.source.remote.HomeRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepoModule {

    @Binds
    @Singleton
    fun provideHomeSectionDataSource(homeSectionDataSource: HomeRemoteDataSourceImpl): HomeRemoteDateSource

    @Binds
    @Singleton
    fun providerRepositoryImpl(homeRepository: HomeRepositoryImpl): HomeRepository
}