package com.ryusw.domain.entitiy.movie

data class MovieListInfo(
    val adult : String,
    val backdropPath : String,
    val id : Int,
    val originalLanguage : String,
    val originalTitle : String,
    val overview : String,
    val popularity : Double,
    val posterPath : String,
    val releaseData : String,
    val title : String,
    val video : Boolean,
    val voteAverage : Double,
    val voteCount : Int
)
