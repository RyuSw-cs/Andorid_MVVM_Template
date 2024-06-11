package com.ryusw.data.remote.dto.movie

import com.google.gson.annotations.SerializedName

internal data class ResponseMovieGenres(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)