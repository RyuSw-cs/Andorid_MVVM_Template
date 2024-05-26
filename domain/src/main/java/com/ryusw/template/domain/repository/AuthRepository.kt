package com.ryusw.template.domain.repository

import com.ryusw.template.domain.entitiy.auth.Token

interface AuthRepository {
    suspend fun login(accessToken : String) : Token?
    suspend fun saveAccessToken(accessToken : String) : Result<Unit>
    suspend fun getAccessToken() : Result<String>
}