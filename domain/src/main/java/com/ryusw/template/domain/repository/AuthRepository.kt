package com.ryusw.template.domain.repository

interface AuthRepository {
    suspend fun setAccessToken(accessToken : String) : Result<Unit>
    suspend fun getAccessToken() : Result<String>
    suspend fun refreshToken(accessToken : String) : Result<Unit>
}