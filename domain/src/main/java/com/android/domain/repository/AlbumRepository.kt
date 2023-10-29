package com.android.domain.repository

import com.android.domain.model.AlbumEntity

interface AlbumRepository {
    fun getAlbums(): List<AlbumEntity>
}