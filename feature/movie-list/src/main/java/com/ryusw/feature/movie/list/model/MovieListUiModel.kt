package com.ryusw.feature.movie.list.model

import com.ryusw.domain.entitiy.movie.Movie

data class MovieListUiModel (
    val id : Int,
    val imgUrl : String,
    val title : String,
    val overview : String,
    val voteAverage : Float
)

fun Movie.toUiModel() = MovieListUiModel(
    id = id,
    imgUrl = posterPath,
    title = title,
    overview = overview,
    voteAverage = voteAverage.toFloat()
)