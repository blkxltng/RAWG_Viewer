package com.blkxltng.rawgviewer.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("gameImageUrl")
fun ImageView.gameImageUrl(imageUrl: String?) {
    Glide.with(this.context).load(imageUrl).centerCrop().into(this)
}