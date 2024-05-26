package com.ryusw.template.domain.repository

import com.ryusw.template.domain.entitiy.user.User

interface UserRepository {
    suspend fun join(accessToken : String) : User?
}