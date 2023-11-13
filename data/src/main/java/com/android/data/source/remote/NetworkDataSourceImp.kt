package com.android.data.source.remote

import com.android.domain.model.AlbumEntity
import javax.inject.Inject

class NetworkDataSourceImp constructor(
    private val apiService: ApiService
): NetworkDataSource {
    override suspend fun getAlbums() = apiService.getAlbums()
}