package com.ryusw.data.remote.dto.movie

import com.google.gson.annotations.SerializedName

internal data class ResponseMovieDetail(
    @SerializedName("adult")
    val adult : Boolean,
    @SerializedName("backdrop_path")
    val backdropPath : String?,
    @SerializedName("belongs_to_collection")
    val belongsToCollection : ResponseMovieBelongsToCollection?,
    @SerializedName("budget")
    val budget : Long,
    @SerializedName("genres")
    val genres : List<ResponseMovieGenres>,
    @SerializedName("homepage")
    val homepage : String,
    @SerializedName("id")
    val id : Int,
    @SerializedName("imdb_id")
    val imdbId : String?,
    @SerializedName("original_language")
    val originCountry : List<String>,
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
    @SerializedName("product_companies")
    val productionCompanies : List<ResponseMovieProductionCompany> = emptyList(),
    @SerializedName("production_countries")
    val productionCountries : List<ResponseMovieProductionCountry> = emptyList(),
    @SerializedName("release_date")
    val releaseDate : String,
    @SerializedName("revenue")
    val revenue : Long,
    @SerializedName("runtime")
    val runtime : Int,
    @SerializedName("spoken_languages")
    val spokenLanguages : List<ResponseMovieSpokenLanguage>,
    @SerializedName("status")
    val status : String,
    @SerializedName("tagline")
    val tagline : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("video")
    val video : Boolean,
    @SerializedName("vote_average")
    val voteAverage : Double,
    @SerializedName("vote_count")
    val voteCount : Int
)