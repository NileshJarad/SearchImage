package com.search_image.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.google.android.material.snackbar.Snackbar
import com.search_image.R
import java.text.SimpleDateFormat
import java.util.*


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

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String) {
    this.load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.ic_image_24px)
    }
}


@SuppressLint("SimpleDateFormat")
@BindingAdapter("commentDate")
fun TextView.loadImage(commentDate: Long) {
    text = SimpleDateFormat("dd, MMM   hh:mm a ").format(Date(commentDate))
}