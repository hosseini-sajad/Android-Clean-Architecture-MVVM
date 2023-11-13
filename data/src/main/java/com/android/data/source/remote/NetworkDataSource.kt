package com.android.data.source.remote

import com.android.data.model.AlbumDto

interface NetworkDataSource {
    suspend fun getAlbums(): List<AlbumDto>
}