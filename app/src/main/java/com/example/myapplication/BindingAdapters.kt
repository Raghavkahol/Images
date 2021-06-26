package com.example.myapplication

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter(value = ["bind:farm", "bind:server", "bind:imageId", "bind:secret"], requireAll = true)
fun bindImageFromUrl(view: ImageView, farm: Int?, server : Int?, imageId : Long?, secret : String?) {
        val imageUrl = "https://farm${farm}.staticflickr.com/${server}/${imageId}_${secret}_m.jpg"
        Glide.with(view.context)
            .load(imageUrl).thumbnail(0.4f).centerCrop()
            .into(view)
}