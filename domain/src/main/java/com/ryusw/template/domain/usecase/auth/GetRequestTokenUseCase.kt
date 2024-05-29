package com.ryusw.template.domain.usecase.auth

import com.ryusw.template.domain.entitiy.auth.RequestToken
import com.ryusw.template.domain.exception.AuthException
import com.ryusw.template.domain.repository.AuthRepository
import javax.inject.Inject

class GetRequestTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke() : RequestToken {
        return try {
            authRepository.getRequestToken()
        }catch (e : Exception){
            throw e
        }
    }
}