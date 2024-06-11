package com.ryusw.common.ui.bindingApdaters

import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat

object ViewBindingAdapters {
    @JvmStatic
    @BindingAdapter("movieRating")
    fun RatingBar.setMovieRating(rating: Float) {
        this.rating = rating.div(2)
    }

    @JvmStatic
    @BindingAdapter("number")
    fun TextView.setNdumber(number : Long) {
        val numberFormat = DecimalFormat("#,###")
        this.text = numberFormat.format(number)
    }

    @JvmStatic
    @BindingAdapter("list")
    fun TextView.setListToString(list: List<String>) {
        var listText = ""
        val listSize = list.size
        list.forEachIndexed { index, text ->
            listText += text
            if (listSize > index + 1) {
                listText += ", "
            }
        }
        this.text = listText
    }
}