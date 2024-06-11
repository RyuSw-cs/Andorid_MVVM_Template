package com.ryusw.data.remote.dto.movie

import com.google.gson.annotations.SerializedName

internal data class ResponseMovieProductionCountry(
    @SerializedName("iso_3166_1")
    val iso_3166_1 : String,
    @SerializedName("name")
    val name : String
)