package com.ryusw.common.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    private var _binding: T? = null
    val binding get() = requireNotNull(_binding)

    abstract val layoutResourceId: Int
    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutResourceId)
        binding.lifecycleOwner = this

        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}