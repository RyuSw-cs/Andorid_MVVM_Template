package com.ryusw.template.data.repository

import com.ryusw.template.data.remote.api.AuthApi
import com.ryusw.template.data.remote.mapper.toDomain
import com.ryusw.template.data.util.HandleApi
import com.ryusw.domain.entitiy.auth.RequestToken
import com.ryusw.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun getRequestToken(apiKey: String): RequestToken =
        HandleApi.callApi { authApi.requestToken(apiKey).toDomain() }

    override suspend fun checkApiKeyValidate(apiKey: String) : Boolean =
        HandleApi.callApi { authApi.requestApiKeyValidate(apiKey).success }
}