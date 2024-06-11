package com.ryusw.data.remote.dto.movie

import com.google.gson.annotations.SerializedName

internal data class ResponseMovieSpokenLanguage(
    @SerializedName("english_name")
    val englishName : String,
    @SerializedName("iso_639_1")
    val iso_639_1 : String,
    @SerializedName("name")
    val name : String
)