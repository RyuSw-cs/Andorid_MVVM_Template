package com.ryusw.template.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

open class BaseViewModel : ViewModel(){
    private val _errorEvent : MutableSharedFlow<Throwable> = MutableSharedFlow()
    val errorEvent : SharedFlow<Throwable> get() = _errorEvent.asSharedFlow()

    private val _loadingEvent :  MutableSharedFlow<Boolean> = MutableSharedFlow()
    val loadingEvent : SharedFlow<Boolean> get() = MutableSharedFlow()

    /*
    * viewModel의 CorutineScopeo에서 나타나는 error를 처리
    * 각 실행중인 코루틴 블럭들이 있는다는 가정하에 하나의 블럭에서 예외가 나면 전체로 처리
    * */
    private val errorHandler = CoroutineExceptionHandler { coroutineScope, throwable ->
        viewModelScope.launch(coroutineScope) {
            // 커스텀 예외가 아닌 예외를 처리
            _errorEvent.emit(throwable)
        }
    }

    protected val baseViewModelScope = viewModelScope + errorHandler
    protected fun changeLoadingState(state : Boolean){
        baseViewModelScope.launch {
            _loadingEvent.emit(state)
        }
    }
}