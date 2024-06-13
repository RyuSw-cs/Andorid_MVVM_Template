package com.ryusw.data.remote.mapper

import com.ryusw.data.di.NetworkModule
import com.ryusw.data.remote.dto.movie.ResponseMovieDetail
import com.ryusw.data.remote.dto.movie.ResponsePopularMovieList
import com.ryusw.domain.entity.movie.Genre
import com.ryusw.domain.entity.movie.Movie
import com.ryusw.domain.entity.movie.ProductionCompany
import com.ryusw.domain.entity.movie.ProductionCountry
import com.ryusw.domain.entity.movie.SpokenLanguage

internal fun ResponsePopularMovieList.toDomain(): List<Movie> {
    return results.map {
        Movie(
            adult = it.adult,
            backdropPath = "${NetworkModule.SERVER_IMAGE_BASE_URL}original/${it.backdropPath}",
            id = it.id,
            originalTitle = it.originalTitle,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = "${NetworkModule.SERVER_IMAGE_BASE_URL}original/${it.posterPath}",
            releaseDate = it.releaseData,
            title = it.title,
            video = it.video,
            voteCount = it.voteCount,
            voteAverage = it.voteAverage
        )
    }
}

internal fun ResponseMovieDetail.toDomain(): Movie {
    return Movie(
        adult = this.adult,
        backdropPath = "${NetworkModule.SERVER_IMAGE_BASE_URL}original/${this.backdropPath}",
        id = this.id,
        budget = this.budget,
        genres = this.genres?.map {
            Genre(it.id, it.name)
        } ?: emptyList(),
        homepage = this.homepage,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        originalCountry = originCountry?.toList() ?: emptyList(),
        productionCompanies = this.productionCompanies?.map {
            ProductionCompany(
                id = it.id,
                logoPath = it.logoPath,
                name = it.name,
                originCountry = it.originCountry
            )
        } ?: emptyList(),
        productionCountries = this.productionCountries?.map {
            ProductionCountry(
                ISO_3166_1 = it.iso_3166_1,
                name = it.name
            )
        } ?: emptyList(),
        spokenLanguages = this.spokenLanguages?.map {
            SpokenLanguage(
                englishName = it.englishName,
                ISO_639_1 = it.iso_639_1,
                name = it.name
            )
        } ?: emptyList(),
        overview = this.overview,
        popularity = this.popularity,
        posterPath = "${NetworkModule.SERVER_IMAGE_BASE_URL}original/${this.posterPath}",
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteCount = this.voteCount,
        voteAverage = this.voteAverage
    )
}