package com.ryusw.common.ui.view

import android.view.View
import android.view.View.OnClickListener
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 중복 클릭을 방지하기 위해 Delay가 설정된 ClickListener
 * */
class DelayButtonClickListener(
    private val onClickListener: OnClickListener,
    private val delayMillis: Long = 1000
) : OnClickListener {
    // 클릭 가능 상태
    private val clickEnableState = AtomicBoolean(true)
    override fun onClick(v: View?) {
        if(clickEnableState.getAndSet(false)){
            onClickListener.onClick(v)
            v?.postDelayed({
                clickEnableState.set(true)
            }, delayMillis)
        }
    }
}