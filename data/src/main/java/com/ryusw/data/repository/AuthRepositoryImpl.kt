package com.ryusw.data.repository

import com.ryusw.data.remote.api.AuthApi
import com.ryusw.data.remote.dto.auth.RequestLoginDto
import com.ryusw.data.remote.mapper.toDomain
import com.ryusw.data.util.HandleApi
import com.ryusw.domain.entitiy.auth.Token
import com.ryusw.domain.entitiy.auth.Session
import com.ryusw.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun getRequestToken(): Token =
        HandleApi.callApi { authApi.requestToken().toDomain() }

    override suspend fun checkApiKeyValidate(): Boolean =
        HandleApi.callApi { authApi.requestApiKeyValidate().success }

    override suspend fun login(
        id: String,
        password: String,
        requestToken: String,
    ): Session =
        HandleApi.callApi {
            authApi.requestLogin(
                requestLoginDto = RequestLoginDto(id, password, requestToken)
            ).toDomain()
        }
}