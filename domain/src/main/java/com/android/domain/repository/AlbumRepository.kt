package com.android.domain.repository

import com.android.domain.model.AlbumEntity
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun getAlbums(): Flow<List<AlbumEntity>>
}