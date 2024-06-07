package com.ryusw.common.ui.bindingApdaters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapters {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.setImageByUrl(url: String) {
        Glide.with(this)
            .load(url)
            .into(this)
    }
}