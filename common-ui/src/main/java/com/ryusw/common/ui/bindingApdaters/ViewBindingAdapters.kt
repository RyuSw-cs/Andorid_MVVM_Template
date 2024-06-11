package com.ryusw.common.ui.bindingApdaters

import android.widget.RatingBar
import androidx.databinding.BindingAdapter

object ViewBindingAdapters {
    @JvmStatic
    @BindingAdapter("movieRating")
    fun RatingBar.setMovieRating(rating : Float){
        this.rating = rating.div(2)
    }
}