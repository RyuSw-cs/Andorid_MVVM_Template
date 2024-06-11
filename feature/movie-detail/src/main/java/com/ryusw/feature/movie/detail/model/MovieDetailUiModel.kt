package com.ryusw.feature.movie.detail.model

import com.ryusw.domain.entitiy.movie.Movie

data class MovieDetailUiModel(
    val genres : List<String> = emptyList(),
    val budget : Long = 0L,
    val title : String = "",
    val posterPath : String = "",
    val overview : String = "",
    val releaseDate : String = "",
    val voteCount : Int = 0,
    val voteAverage : Float = 0f
)

fun Movie.toUiModel() = MovieDetailUiModel(
    genres = this.genres.map {
        it.name
    },
    budget = this.budget ?: 0L,
    title = this.title,
    posterPath = this.posterPath,
    overview = this.overview,
    releaseDate = this.releaseDate,
    voteCount = this.voteCount,
    voteAverage = this.voteAverage.toFloat()
)