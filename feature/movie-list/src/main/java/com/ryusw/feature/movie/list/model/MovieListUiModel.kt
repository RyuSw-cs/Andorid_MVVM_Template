package com.ryusw.feature.movie.list.model

import com.ryusw.domain.entitiy.movie.MovieListInfo

data class MovieListUiModel (
    val id : Int,
    val imgUrl : String,
    val title : String,
    val overview : String,
    val voteAverage : Float
)

// TODO UiModel Mapper는 어떻게 구현해야할까?
fun MovieListInfo.toUiModel() = MovieListUiModel(
    id = id,
    imgUrl = posterPath,
    title = title,
    overview = overview,
    voteAverage = voteAverage.toFloat()
)