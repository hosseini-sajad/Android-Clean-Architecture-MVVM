package com.android.domain.usecase

import com.android.domain.model.AlbumEntity
import com.android.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow

class GetAlbumsUsecase constructor(
    private val albumRepository: AlbumRepository
) {
    operator fun invoke() : Flow<List<AlbumEntity>> {
        return albumRepository.getAlbums()
    }
}