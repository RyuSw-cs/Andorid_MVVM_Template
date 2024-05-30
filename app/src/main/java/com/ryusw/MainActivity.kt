package com.ryusw

import com.ryusw.databinding.ActivityMainBinding
import com.ryusw.common.ui.base.BaseActivity
import com.ryusw.R
import com.ryusw.common.ui.base.NavigationEvent

class MainActivity : BaseActivity<ActivityMainBinding>(), NavigationEvent {
    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override fun init() {
        // nav 초기화
    }

    override fun navigateSplashToLogin() {
        // feature간 navigate 할때 필요
    }
}