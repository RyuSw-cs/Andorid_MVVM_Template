package com.ryusw.template.domain.usecase.user

import com.ryusw.template.domain.entitiy.user.User
import com.ryusw.template.domain.exception.UserException
import com.ryusw.template.domain.repository.UserRepository
import javax.inject.Inject

class JoinUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(accessToken : String) : User? {
        return try {
            userRepository.join(accessToken)
        }catch (e: UserException.UserNotFoundException){
            // TODO User는 못찾았지만 추가적인 Return 가능하도록 설정
            null
        }catch (e: Exception){
            null
        }
    }
}