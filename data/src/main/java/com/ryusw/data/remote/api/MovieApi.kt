package com.ryusw.data.remote.api

import com.ryusw.data.BuildConfig
import com.ryusw.data.remote.dto.movie.ResponseMovieDetail
import com.ryusw.data.remote.dto.movie.ResponsePopularMovieList
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovieList(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String,
        @Query("page") page: Int
    ): ResponsePopularMovieList

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String
    ) : ResponseMovieDetail
}