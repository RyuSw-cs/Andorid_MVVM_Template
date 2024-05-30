package com.ryusw.feature.login

sealed interface LoginAction {
    class ShowDialog(title : String = "알림", message : String) : LoginAction
    data object NavigateMovieSearch : LoginAction
    data object DoLogin : LoginAction
}