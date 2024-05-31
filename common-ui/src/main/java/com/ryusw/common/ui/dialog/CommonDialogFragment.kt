package com.ryusw.common.ui.dialog

import com.ryusw.common.R
import com.ryusw.common.databinding.DialogCommonBinding
import com.ryusw.common.ui.base.BaseDialogFragment

class CommonDialogFragment(
    private val title: String,
    private val content: String
) : BaseDialogFragment<DialogCommonBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.dialog_common

    override fun initDataBinding() {
        with(binding) {
            tvTitle.text = title
            tvContent.text = content
        }
    }

    override fun initView() {
        binding.btnClose.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }
}