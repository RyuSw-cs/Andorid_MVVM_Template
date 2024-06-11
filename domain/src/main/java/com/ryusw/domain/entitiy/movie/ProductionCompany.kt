package com.ryusw.domain.entitiy.movie

data class ProductionCompany(
    val id: Int,
    val logoPath: String? = null,
    val name: String,
    val originCountry: String
)