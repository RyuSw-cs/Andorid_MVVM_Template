package com.ryusw.template.domain.usecase.auth

import com.ryusw.template.domain.exception.AuthException
import com.ryusw.template.domain.repository.AuthRepository
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke() : String {
        val accessToken = authRepository.getAccessToken()
        if(accessToken.isEmpty()){
            throw AuthException.EmptyTokenException("토큰이 없습니다.")
        }
        return accessToken
    }
}