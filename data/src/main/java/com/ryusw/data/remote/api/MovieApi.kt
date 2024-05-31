package com.ryusw.data.remote.api

import com.ryusw.data.remote.dto.movie.ResponsePopularMovieList
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovieList(
        @Query("api_key") apiKey : String,
        @Query("language") language : String,
        @Query("page") page : Int
    ) : ResponsePopularMovieList
}