package com.s24.mastodonclient

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.s24.mastodonclient.entity.Media

@BindingAdapter("spannedContent")
fun TextView.setSpannedString(content: String){
    text = HtmlCompat.fromHtml(
        content,
        HtmlCompat.FROM_HTML_MODE_COMPACT
    )
}
@BindingAdapter("media")
fun ImageView.setMedia(media: Media?){
    if(media == null){
        setImageDrawable(null)
        return
    }
    Glide.with(this)
        .load(media.url)
        .into(this)
}