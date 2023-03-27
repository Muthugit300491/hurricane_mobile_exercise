package com.example.productapp.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.util.*


@BindingAdapter("setText")
fun bindDefaultText(mView: TextView, mText: String?) {
    if (!mText.isNullOrEmpty()) {
        mView.text = mText
    }
}
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        //.apply(RequestOptions().circleCrop())
        //.transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}






