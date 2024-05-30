package com.ryusw.feature.login

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ryusw.common.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ryusw.feature.login.R
import ryusw.feature.login.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_login

    override val viewModel: LoginViewModel by viewModels()

    override fun initView() {
        with(binding){
            editId.addTextChangedListener {
                viewModel.updateId(it?.toString()!!)
            }

            editPassword.addTextChangedListener {
                viewModel.updatePassword(it?.toString()!!)
            }
        }
    }

    override fun initDataBinding() {
        binding.vm = viewModel
    }

    override fun initObserving() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.action.collect { action ->
                    when (action) {
                        is LoginAction.DoLogin -> viewModel.requestLogin()
                        is LoginAction.ShowDialog -> {

                        }
                        is LoginAction.NavigateMovieSearch -> {

                        }
                    }
                }
            }
        }
    }
}