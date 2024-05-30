package com.ryusw.feature.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryusw.domain.exception.AuthException
import com.ryusw.domain.usecase.auth.GetRequestTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getRequestTokenUseCase: GetRequestTokenUseCase
) : ViewModel() {
    private val _action: MutableSharedFlow<LoginAction> = MutableSharedFlow()
    val action : SharedFlow<LoginAction> get() = _action.asSharedFlow()
    private val _state : MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val state : StateFlow<LoginState> get() = _state.asStateFlow()

    private val _id : MutableStateFlow<String> = MutableStateFlow("")
    val id : StateFlow<String> get() = _id.asStateFlow()

    private val _password : MutableStateFlow<String> = MutableStateFlow("")
    val password : StateFlow<String> get() = _password.asStateFlow()

    fun requestLogin() = viewModelScope.launch {
        runCatching {
            getRequestTokenUseCase(BuildConfig.API_KEY)
        }.onSuccess {
            // TODO id, pw로 로그인 시작
            Log.d("TAG", "getRequestToken: $it")
        }.onFailure {
            when (it) {
                is AuthException.AuthenticationFailedException -> {
                    _action.emit(LoginAction.ShowDialog(message = it.message!!))
                }
                else -> _action.emit(
                    LoginAction.ShowDialog(
                        message = it.message ?: "알 수 없는 오류입니다."
                    )
                )
            }
        }
    }

    fun updateId(id : String) = viewModelScope.launch {
        _id.emit(id)
    }

    fun updatePassword(password : String) = viewModelScope.launch {
        _password.emit(password)
    }


}