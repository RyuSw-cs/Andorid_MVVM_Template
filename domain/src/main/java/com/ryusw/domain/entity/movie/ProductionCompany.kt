package com.ryusw.domain.entity.movie

data class ProductionCompany(
    val id: Int,
    val logoPath: String? = null,
    val name: String,
    val originCountry: String
)