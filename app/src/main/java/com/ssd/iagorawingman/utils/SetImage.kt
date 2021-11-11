package com.ssd.iagorawingman.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView
import com.ssd.iagorawingman.R

object SetImage {
    fun ImageView.load(url: String) {
        Glide.with(this.context).load(url)
            .centerCrop()
            .into(this)
    }

    fun ShapeableImageView.loadPhotoProfile(url: String) {
        Glide.with(this.context).setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_photo_profile_empty)
                .error(R.drawable.ic_photo_profile_empty)
                .centerCrop()
        ).load(url)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}