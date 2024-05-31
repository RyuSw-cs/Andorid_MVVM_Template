package com.ryusw.data.remote.dto.movie

import com.google.gson.annotations.SerializedName

internal data class ResponsePopularMovieList(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val results : List<ResponsePopularMovie>,
    @SerializedName("total_page")
    val totalPage : Int,
    @SerializedName("total_result")
    val totalResult : Int
)
