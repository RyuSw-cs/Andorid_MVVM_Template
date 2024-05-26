package com.ryusw.template.common.dialog

import com.ryusw.template.common.R
import com.ryusw.template.common.base.BaseDialogFragment
import com.ryusw.template.common.databinding.DialogLoadingBinding

class LoadingDialogFragment : BaseDialogFragment<DialogLoadingBinding>(){
    override val layoutResourceId: Int
        get() = R.layout.dialog_loading

    override fun initDataBinding() {}
    override fun initView() {}

}