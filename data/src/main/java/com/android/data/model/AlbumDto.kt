package com.android.data.model

import com.android.domain.model.AlbumEntity

data class AlbumDto(
    var userId: Long,
    var id: Long,
    var title: String,
)
fun AlbumDto.toEntity() = AlbumEntity(
    userId,
    id,
    title
)
