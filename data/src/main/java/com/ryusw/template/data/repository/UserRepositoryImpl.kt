package com.ryusw.template.data.repository

import com.ryusw.template.data.remote.api.UserApi
import com.ryusw.template.data.remote.mapper.UserMapper.toDomain
import com.ryusw.template.data.util.HandleApi
import com.ryusw.template.domain.entitiy.user.User
import com.ryusw.template.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun join(accessToken: String): User? {
        return HandleApi.callApi { userApi.join(accessToken).toDomain() }
    }
}
