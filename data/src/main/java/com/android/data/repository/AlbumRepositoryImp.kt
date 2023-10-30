package com.android.data.repository

import com.android.data.model.toEntity
import com.android.data.source.remote.ApiService
import com.android.domain.model.AlbumEntity
import com.android.domain.repository.AlbumRepository
import javax.inject.Inject


class AlbumRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : AlbumRepository {
    override fun getAlbums(): List<AlbumEntity> {
        return apiService.getAlbums().map { it.toEntity() }
    }
}