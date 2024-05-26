package com.ryusw.template.data.remote.mapper

import com.ryusw.template.data.remote.dto.auth.AuthResponseDto
import com.ryusw.template.domain.entitiy.auth.Token

internal fun AuthResponseDto.toDomain() = Token(
    this.token
)