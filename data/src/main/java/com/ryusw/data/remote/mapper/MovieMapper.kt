package com.ryusw.data.remote.mapper

import com.ryusw.data.di.NetworkModule
import com.ryusw.data.remote.api.MovieApi
import com.ryusw.data.remote.dto.movie.ResponsePopularMovie
import com.ryusw.data.remote.dto.movie.ResponsePopularMovieList
import com.ryusw.domain.entitiy.movie.MovieListInfo

internal fun ResponsePopularMovieList.toDomain(): List<MovieListInfo> {
    return results.map {
        MovieListInfo(
            adult = it.adult,
            backdropPath = "${NetworkModule.SERVER_IMAGE_BASE_URL}original/${it.backdropPath}",
            id = it.id,
            originalTitle = it.originalTitle,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = "${NetworkModule.SERVER_IMAGE_BASE_URL}original/${it.posterPath}",
            releaseData = it.releaseData,
            title = it.title,
            video = it.video,
            voteCount = it.voteCount,
            voteAverage = it.voteAverage
        )
    }
}