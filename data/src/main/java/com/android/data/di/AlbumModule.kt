package com.android.data.di

import com.android.data.repository.AlbumRepositoryImp
import com.android.data.source.remote.NetworkDataSource
import com.android.data.source.remote.NetworkDataSourceImp
import com.android.domain.repository.AlbumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AlbumModule {

    @Binds
    abstract fun bindAlbumRepository(albumRepositoryImp: AlbumRepositoryImp): AlbumRepository

    @Binds
    abstract fun bindNetworkDataSource(networkDataSourceImp: NetworkDataSourceImp): NetworkDataSource
}