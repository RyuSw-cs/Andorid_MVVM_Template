package com.ryusw.login

sealed interface LoginAction {
    class showDialog(title : String = "알림", message : String) : LoginAction
    data object NavigateMovieSearch : LoginAction
    data object DoLogin : LoginAction
}