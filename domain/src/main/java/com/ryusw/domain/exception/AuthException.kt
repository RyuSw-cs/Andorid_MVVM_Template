package com.ryusw.domain.exception

sealed class AuthException : Exception{
    constructor() : super()
    constructor(msg : String) : super(msg)
    constructor(msg : String, throwable : Throwable) : super(msg, throwable)
    class AuthenticationFailedException(msg : String) : AuthException(msg)
    class InvalidApiKeyException(msg : String) : AuthException(msg)
    class InvalidRequestTokenException(msg : String) : AuthException(msg)
    class InvalidAccountException(msg : String) : AuthException(msg)
}