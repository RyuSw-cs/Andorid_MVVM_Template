package com.ryusw.domain.repository

import com.ryusw.domain.entitiy.movie.Movie

interface MovieRepository {
    suspend fun getPopularMovieList(language : String, page : Int) : List<Movie>
    suspend fun getMovieDetail(language : String, movieId : Int) : Movie
}