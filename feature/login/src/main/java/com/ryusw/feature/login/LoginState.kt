package com.ryusw.feature.login

data class LoginState(
    val id : String = "",
    val password : String = ""
) {
    val loginEnable = id.isNotBlank() && password.isNotBlank()
}
