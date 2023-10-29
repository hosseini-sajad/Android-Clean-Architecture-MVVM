package com.android.data.source.remote

import com.android.data.model.AlbumDto
import retrofit2.http.GET

interface ApiService {

    @GET
    fun getAlbums(): List<AlbumDto>
}