package com.ryusw.domain.repository

import com.ryusw.domain.entitiy.auth.RequestToken

interface AuthRepository {
    suspend fun getRequestToken(apiKey : String) : RequestToken
    suspend fun checkApiKeyValidate(apiKey : String) : Boolean
}