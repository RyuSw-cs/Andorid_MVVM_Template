package com.ryusw.data.repository

import com.ryusw.data.remote.api.MovieApi
import com.ryusw.data.remote.mapper.toDomain
import com.ryusw.data.util.HandleApi
import com.ryusw.domain.entity.movie.Movie
import com.ryusw.domain.repository.MovieRepository
import javax.inject.Inject

internal class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override suspend fun getPopularMovieList(
        language: String,
        page: Int
    ): List<Movie> {
        return HandleApi.callApi {
            movieApi.getPopularMovieList(language = language, page = page).toDomain()
        }
    }

    override suspend fun getMovieDetail(language: String, movieId: Int): Movie {
        return HandleApi.callApi {
            movieApi.getMovieDetail(language = language, movieId = movieId).toDomain()
        }
    }
}