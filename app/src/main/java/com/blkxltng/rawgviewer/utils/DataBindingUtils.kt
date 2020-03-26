package com.blkxltng.rawgviewer.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("gameImageUrl")
fun ImageView.gameImageUrl(imageUrl: String?) {
    Glide.with(this.context).load(imageUrl).centerCrop().into(this)
}

@BindingAdapter("gameImageUrlDetails")
fun ImageView.gameImageUrlDetails(imageUrl: String?) {
    Glide.with(this.context).load(imageUrl).fitCenter().into(this)
}

@BindingAdapter("htmlText")
fun TextView.htmlText(text: String?) {
    if (text != null) {
        this.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }
}