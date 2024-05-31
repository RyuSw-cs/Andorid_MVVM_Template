package com.ryusw.data.repository

import com.ryusw.data.remote.api.MovieApi
import com.ryusw.data.remote.mapper.toDomain
import com.ryusw.data.util.HandleApi
import com.ryusw.domain.entitiy.movie.MovieListInfo
import com.ryusw.domain.repository.MovieRepository
import javax.inject.Inject

internal class MovieRepositoryImpl @Inject constructor(
    private val movieApi : MovieApi
) : MovieRepository {
    override suspend fun getPopularMovieList(
        apiKey: String,
        language: String,
        page: Int
    ): List<MovieListInfo> {
        return HandleApi.callApi { movieApi.getPopularMovieList(apiKey, language, page).toDomain() }
    }
}