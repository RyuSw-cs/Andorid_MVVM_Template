package com.ryusw.template.common.bindingApdaters

import android.view.View
import androidx.databinding.BindingAdapter
import com.ryusw.template.common.view.DelayButtonClickListener

object ButtonBindingAdapters {
    @JvmStatic
    @BindingAdapter("onClick")
    fun View.setButtonOnClickListener(listener: View.OnClickListener?) {
        if(listener != null){
            setOnClickListener(DelayButtonClickListener(listener))
        }
    }
}