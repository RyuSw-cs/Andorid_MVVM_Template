package com.ryusw.domain.entitiy.movie

/*
* TODO 엔티티 설계는 어떻게 해야할까?
* */
data class Movie(
    val adult: Boolean,
    val backdropPath: String? = null,
    val id: Int,
    val budget: Long? = 0L,
    val genres: List<Genre> = emptyList(),
    val homepage: String? = null,
    val originalLanguage: String,
    val originalTitle: String,
    val originalCountry: List<String> = emptyList(),
    val productionCompanies: List<ProductionCompany> = emptyList(),
    val productionCountries: List<ProductionCountry> = emptyList(),
    val spokenLanguages: List<SpokenLanguage> = emptyList(),
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
