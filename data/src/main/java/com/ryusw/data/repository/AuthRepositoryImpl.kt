package com.ryusw.data.repository

import com.ryusw.data.remote.api.AuthApi
import com.ryusw.data.remote.dto.auth.RequestLoginDto
import com.ryusw.data.remote.mapper.toDomain
import com.ryusw.data.util.HandleApi
import com.ryusw.domain.entitiy.auth.RequestToken
import com.ryusw.domain.entitiy.auth.SessionWithLogin
import com.ryusw.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun getRequestToken(apiKey: String): RequestToken =
        HandleApi.callApi { authApi.requestToken(apiKey).toDomain() }

    override suspend fun checkApiKeyValidate(apiKey: String): Boolean =
        HandleApi.callApi { authApi.requestApiKeyValidate(apiKey).success }

    override suspend fun login(
        id: String,
        password: String,
        requestToken: String,
        apiKey: String
    ): SessionWithLogin =
        HandleApi.callApi {
            authApi.requestLogin(
                apiKey,
                RequestLoginDto(id, password, requestToken)
            ).toDomain()
        }
}