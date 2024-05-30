package com.ryusw.template.data.remote.mapper

import com.ryusw.template.data.remote.dto.auth.ResponseRequestTokenDto
import com.ryusw.domain.entitiy.auth.RequestToken

internal fun ResponseRequestTokenDto.toDomain() = RequestToken(
    expiredDt = expiredDt,
    requestToken = requestToken
)