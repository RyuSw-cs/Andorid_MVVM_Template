package com.ryusw.feature.login

sealed interface LoginAction {
    class ShowDialog(val title : String = "알림", val content : String) : LoginAction
    data object NavigateMovieSearch : LoginAction
}