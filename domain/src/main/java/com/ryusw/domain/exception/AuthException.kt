package com.ryusw.domain.exception

sealed class AuthException : Exception{
    constructor() : super()
    constructor(msg : String) : super(msg)
    constructor(msg : String, throwable : Throwable) : super(msg, throwable)
    class EmptyTokenException(msg : String) : AuthException(msg)
    class AuthenticationFailedException(msg : String) : AuthException(msg)
    class InvalidApiKeyException(msg : String) : AuthException(msg)
    class SuspendedApikeyException(msg : String) : AuthException(msg)
    class DeviceDeniedException(msg : String) : AuthException(msg)
    class SessionDeniedException(msg : String) : AuthException(msg)
    class InvalidAccountException(msg : String) : AuthException(msg)
}