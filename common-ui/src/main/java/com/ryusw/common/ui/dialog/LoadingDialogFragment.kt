package com.ryusw.common.ui.dialog

import com.ryusw.common.R
import com.ryusw.common.databinding.DialogLoadingBinding
import com.ryusw.common.ui.base.BaseDialogFragment

class LoadingDialogFragment : BaseDialogFragment<DialogLoadingBinding>(){
    override val layoutResourceId: Int
        get() = R.layout.dialog_loading

    override fun initDataBinding() {}
    override fun initView() {}

}