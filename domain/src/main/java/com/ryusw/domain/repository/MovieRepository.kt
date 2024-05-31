package com.ryusw.domain.repository

import com.ryusw.domain.entitiy.movie.MovieListInfo

interface MovieRepository {
    suspend fun getPopularMovieList(apiKey : String, language : String, page : Int) : List<MovieListInfo>
}