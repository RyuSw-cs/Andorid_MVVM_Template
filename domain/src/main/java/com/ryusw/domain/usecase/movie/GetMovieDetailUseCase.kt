package com.ryusw.domain.usecase.movie

import com.ryusw.domain.entitiy.movie.Movie
import com.ryusw.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(language: String, movieId: Int): Movie {
        return movieRepository.getMovieDetail(language, movieId)
    }
}