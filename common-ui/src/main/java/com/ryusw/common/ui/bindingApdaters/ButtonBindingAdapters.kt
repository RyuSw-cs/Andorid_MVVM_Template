package com.ryusw.common.ui.bindingApdaters

import android.view.View
import androidx.databinding.BindingAdapter
import com.ryusw.common.ui.view.DelayButtonClickListener

object ButtonBindingAdapters {
    @JvmStatic
    @BindingAdapter("onClick")
    fun View.setButtonOnClickListener(listener: View.OnClickListener?) {
        if(listener != null){
            setOnClickListener(DelayButtonClickListener(listener))
        }
    }
}