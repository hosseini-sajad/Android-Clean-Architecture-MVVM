package com.android.data.repository

import com.android.data.model.toEntity
import com.android.data.source.remote.ApiService
import com.android.data.source.remote.NetworkDataSource
import com.android.domain.model.AlbumEntity
import com.android.domain.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AlbumRepositoryImp constructor(
    private val networkDataSource: NetworkDataSource
) : AlbumRepository {
    override fun getAlbums() = flow<List<AlbumEntity>> {
        withContext(IO){
            networkDataSource.getAlbums().map { it.toEntity() }
        }
    }
}