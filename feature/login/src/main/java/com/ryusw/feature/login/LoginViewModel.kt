package com.ryusw.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryusw.domain.exception.AuthException
import com.ryusw.domain.usecase.auth.GetRequestTokenUseCase
import com.ryusw.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ryusw.feature.login.BuildConfig
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getRequestTokenUseCase: GetRequestTokenUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _action: MutableSharedFlow<LoginAction> = MutableSharedFlow()
    val action: SharedFlow<LoginAction> get() = _action.asSharedFlow()

    private val _loading : MutableSharedFlow<Boolean> = MutableSharedFlow()
    val loading : SharedFlow<Boolean> get() = _loading.asSharedFlow()

    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> get() = _state.asStateFlow()

    fun requestLogin() {
        viewModelScope.launch {
            _loading.emit(true)
            runCatching {
                getRequestTokenUseCase(BuildConfig.TMDB_API_KEY)
            }.onSuccess {
                _loading.emit(false)
                login(it.requestToken)
            }.onFailure {
                _loading.emit(false)
                when (it) {
                    is AuthException.AuthenticationFailedException -> {
                        _action.emit(LoginAction.ShowDialog(content = it.message!!))
                    }

                    else -> _action.emit(
                        LoginAction.ShowDialog(
                            content = it.message ?: "알 수 없는 오류입니다."
                        )
                    )
                }
            }
        }
    }

    fun login(requestToken: String) {
        viewModelScope.launch {
            runCatching {
                _loading.emit(true)
                loginUseCase(
                    id = state.value.id,
                    password = state.value.password,
                    requestToken = requestToken,
                    apiKey = BuildConfig.TMDB_API_KEY
                )
            }.onSuccess {
                _loading.emit(false)
                _action.emit(LoginAction.NavigateMovieSearch)
            }.onFailure {
                _loading.emit(false)
                when (it) {
                    is AuthException -> _action.emit(LoginAction.ShowDialog(content = "로그인 오류 발생"))
                    else -> _action.emit(LoginAction.ShowDialog(content = "알 수 없는 오류 발생"))
                }
            }
        }
    }

    fun updateId(id: String) = viewModelScope.launch {
        _state.update { before ->
            before.copy(
                id = id
            )
        }
    }

    fun updatePassword(password: String) = viewModelScope.launch {
        _state.update { before ->
            before.copy(
                password = password
            )
        }
    }
}

data class LoginState(
    val id : String = "",
    val password : String = ""
) {
    val loginEnable = id.isNotBlank() && password.isNotBlank()
}


sealed interface LoginAction {
    class ShowDialog(val title : String = "알림", val content : String) : LoginAction
    data object NavigateMovieSearch : LoginAction
}