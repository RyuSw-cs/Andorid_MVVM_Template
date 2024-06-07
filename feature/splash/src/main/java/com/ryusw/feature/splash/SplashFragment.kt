package com.ryusw.feature.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ryusw.common.ui.base.BaseFragment
import com.ryusw.common.ui.base.NavigationEvent
import com.ryusw.feature.splash.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_splash
    override val viewModel: SplashViewModel by viewModels()

    override fun initView() {}
    override fun initDataBinding() {}

    override fun initObserving() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.action.collect { action ->
                        when(action){
                            is SplashAction.NavigateToLogin -> {
                                (activity as NavigationEvent).navigateSplashToLogin()
                            }

                            is SplashAction.ShowToast -> {
                                showToast(action.message)
                            }
                        }
                    }
                }

                launch {
                    viewModel.loading.collect {loading ->
                        if(loading){
                            showLoadingDialog()
                        } else {
                            dismissLoadingDialog()
                        }
                    }
                }
            }
        }
    }
}