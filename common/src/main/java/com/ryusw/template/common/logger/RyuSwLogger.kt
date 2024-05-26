package com.ryusw.template.common.logger

import android.util.Log

/**
 * 로그 관리 object
 * 추후에 log trace에 사용
 * */
object RyuSwLogger {
    /**
     * Debug에 필요한 로그
     * */
    fun d(className : String, methodName : String, message : String) {
        Log.d(className, "${methodName}() : $message")
    }

    /**
     * 변수의 상태나 정보를 확인하기 위한 로그
     * */
    fun i(className: String, methodName: String, message: String) {
        Log.i(className, "${methodName}() : $message")

    }

    /**
     * 앱에 치명적이진 않지만 올바르지 않은 로직일 경우 사용
     * */
    fun w(className: String, methodName: String, message: String) {
        Log.w(className, "${methodName}() : $message")
    }

    /**
     * 앱에 치명적인 오류 발생시 사용
     * */
    fun e(className: String, methodName: String, message: String) {
        Log.e(className, "${methodName}() : $message")
    }

    /**
     * 앱의 동작을 기록하는 로그
     * */
    fun v(className: String, methodName: String, message: String) {
        Log.v(className, "${methodName}() : $message")
    }
}