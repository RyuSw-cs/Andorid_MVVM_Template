package com.ryusw.data.remote.dto.movie

import com.google.gson.annotations.SerializedName

internal data class ResponseMovieProductionCompany(
    @SerializedName("id")
    val id : Int,
    @SerializedName("logo_path")
    val logoPath : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("origin_country")
    val originCountry : String
)