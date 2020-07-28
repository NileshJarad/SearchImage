package com.search_image.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.Snackbar


@BindingAdapter("visibility")
fun View.setVisibility(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("showSnack")
fun View.shoeSnack(message: String?) {
    message?.let {
        Snackbar.make(this, it, Snackbar.LENGTH_LONG).show()
    }

}