package com.ryusw.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ryusw.template.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ryusw.template.login.R
import ryusw.template.login.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_login

    override val viewModel: LoginViewModel by viewModels()

    override fun initView() {}

    override fun initDataBinding() {
        binding.vm = viewModel
    }

    override fun initObserving() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.action.collect { action ->
                    when (action) {
                        is LoginAction.DoLogin -> viewModel.requestLogin()
                        is LoginAction.showDialog -> {

                        }
                        is LoginAction.NavigateMovieSearch -> {

                        }
                    }
                }
            }
        }
    }
}