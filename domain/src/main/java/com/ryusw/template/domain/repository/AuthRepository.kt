package com.ryusw.template.domain.repository

interface AuthRepository {
    suspend fun setAccessToken(accessToken : String) : Boolean
    suspend fun getAccessToken() : String
    suspend fun refreshToken(accessToken : String) : Boolean
}