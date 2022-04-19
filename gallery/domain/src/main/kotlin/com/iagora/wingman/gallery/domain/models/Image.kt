package com.iagora.wingman.gallery.domain.models


import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val imageName: String,
    val imagePath: String,
    val imageUri: Uri,
    var selected: Boolean = false,
) : Parcelable
