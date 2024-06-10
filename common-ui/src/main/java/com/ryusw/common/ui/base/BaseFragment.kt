package com.ryusw.common.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import com.ryusw.common.ui.dialog.LoadingDialogFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseFragment<T : ViewDataBinding, R : ViewModel> : Fragment() {

    // 사용하고자 하는 layoutId
    abstract val layoutResourceId: Int

    // Fragment에 사용되는 viewModel
    abstract val viewModel: R

    private var _binding: T? = null
    val binding get() = requireNotNull(_binding)

    private val loadingDialog by lazy { LoadingDialogFragment() }

    /**
     * View에 필요한 객체(adpater..)를 설정
     * */
    abstract fun initView()

    /**
     * Databinding에 필요한 값을 설정
     * */
    abstract fun initDataBinding()

    /**
     * viewModel에서 사용되는 값을 Observing
     * */
    abstract fun initObserving()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        initDataBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserving()
    }

    protected fun showLoadingDialog() {
        if (!loadingDialog.isAdded) {
            loadingDialog.show(childFragmentManager, LoadingDialogFragment::class.java.simpleName)
        }
    }

    protected fun dismissLoadingDialog() {
        if (loadingDialog.isAdded) {
            loadingDialog.dismissAllowingStateLoss()
        }
    }

    protected fun showToast(message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(requireContext(), message, duration).show()
    }

    /**
     * feature 모듈 간 실행해야함
     * */
    protected fun navigate(direction: NavDirections) {
        val controller = findNavController(binding.root)
        // TODO 예외처리 구현 여부 확인
        controller.navigate(direction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}