package com.ryusw.domain.usecase.movie

import com.ryusw.domain.entitiy.movie.Movie
import com.ryusw.domain.repository.MovieRepository
import javax.inject.Inject

class GetPopularMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
){
    suspend operator fun invoke(language : String, page : Int) : List<Movie>{
        return movieRepository.getPopularMovieList(language, page)
    }
}