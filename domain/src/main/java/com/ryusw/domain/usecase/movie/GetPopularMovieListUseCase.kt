package com.ryusw.domain.usecase.movie

import com.ryusw.domain.entitiy.movie.MovieListInfo
import com.ryusw.domain.repository.MovieRepository
import javax.inject.Inject

class GetPopularMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
){
    suspend operator fun invoke(apiKey : String, language : String, page : Int) : List<MovieListInfo>{
        return movieRepository.getPopularMovieList(apiKey, language, page)
    }
}