package com.ryusw.data.remote.dto.movie

import com.google.gson.annotations.SerializedName

internal data class ResponseMovieBelongsToCollection(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("backdrop_path")
    val backdropPath : String
)