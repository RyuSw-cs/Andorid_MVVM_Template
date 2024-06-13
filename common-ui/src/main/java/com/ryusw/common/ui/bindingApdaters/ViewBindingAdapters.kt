package com.ryusw.common.ui.bindingApdaters

import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat

object ViewBindingAdapters {
    @JvmStatic
    @BindingAdapter("movieRating")
    fun RatingBar.setMovieRating(rating: Float?) {
        rating?.let {
            this.rating = it.div(2)
        }
    }

    @JvmStatic
    @BindingAdapter("largeNumber")
    fun TextView.setLongNumber(number: Long?) {
        number?.let {
            val numberFormat = DecimalFormat("#,###")
            this.text = numberFormat.format(it)
        }
    }

    @JvmStatic
    @BindingAdapter("number")
    fun TextView.setNumber(number: Int?) {
        number?.let {
            val numberFormat = DecimalFormat("#,###")
            this.text = numberFormat.format(it)
        }
    }

    @JvmStatic
    @BindingAdapter("list")
    fun TextView.setListToString(list: List<String>?) {
        list?.let {
            var listText = ""
            val listSize = it.size
            it.forEachIndexed { index, text ->
                listText += text
                if (listSize > index + 1) {
                    listText += ", "
                }
            }
            this.text = listText
        }
    }
}