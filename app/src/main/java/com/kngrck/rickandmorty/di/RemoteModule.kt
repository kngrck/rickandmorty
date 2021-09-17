package com.kngrck.rickandmorty.di

import com.kngrck.rickandmorty.data.ApiRepositoryImpl
import com.kngrck.rickandmorty.domain.repository.ApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
    @Binds
    abstract fun bindRemoteRepository(
        apiRepositoryImpl: ApiRepositoryImpl
    ): ApiRepository
}