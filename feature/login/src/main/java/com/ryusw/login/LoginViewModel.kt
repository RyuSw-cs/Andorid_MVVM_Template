package com.ryusw.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ryusw.template.common.base.BaseViewModel
import com.ryusw.template.domain.exception.AuthException
import com.ryusw.template.domain.usecase.auth.GetRequestTokenUseCase
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
) : BaseViewModel() {
    private val _action: MutableSharedFlow<LoginAction> = MutableSharedFlow()
    val action : SharedFlow<LoginAction> get() = _action.asSharedFlow()

    private val _id : MutableStateFlow<String> = MutableStateFlow("")
    val id : StateFlow<String> get() = _id.asStateFlow()

    private val _password : MutableStateFlow<String> = MutableStateFlow("")
    val password : StateFlow<String> get() = _password.asStateFlow()

    fun requestLogin() = baseViewModelScope.launch {
        runCatching {
            getRequestTokenUseCase()
        }.onSuccess {
            // TODO id, pw로 로그인 시작
            Log.d("TAG", "getRequestToken: $it")
        }.onFailure {
            when (it) {
                is AuthException.AuthenticationFailedException -> {
                    _action.emit(LoginAction.showDialog(message = it.message!!))
                }
                else -> _action.emit(LoginAction.showDialog(message = it.message ?: "알 수 없는 오류입니다."))
            }
        }
    }
}