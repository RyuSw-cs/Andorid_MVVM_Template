package com.ryusw.data.remote.dto.movie

import com.google.gson.annotations.SerializedName

internal data class ResponsePopularMovie (
    @SerializedName("adult")
    val adult : String,
    @SerializedName("backdrop_path")
    val backdropPath : String,
    @SerializedName("genre_ids")
    val genreIds : List<Int>,
    @SerializedName("id")
    val id : Int,
    @SerializedName("original_language")
    val originalLanguage : String,
    @SerializedName("original_title")
    val originalTitle : String,
    @SerializedName("overview")
    val overview : String,
    @SerializedName("popularity")
    val popularity : Double,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("release_date")
    val releaseData : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("video")
    val video : Boolean,
    @SerializedName("vote_average")
    val voteAverage : Double,
    @SerializedName("vote_count")
    val voteCount : Int
)