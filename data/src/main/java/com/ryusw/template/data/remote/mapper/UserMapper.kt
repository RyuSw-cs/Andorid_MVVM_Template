package com.ryusw.template.data.remote.mapper

import com.ryusw.template.data.remote.dto.user.UserResponseDto
import com.ryusw.template.domain.entitiy.user.User

internal object UserMapper {
    fun UserResponseDto.toDomain() = User(
        id = id,
        name = name
    )
}