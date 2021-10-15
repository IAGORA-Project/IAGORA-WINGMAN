package com.ssd.iagorawingman.data.source.local.model


import android.net.Uri
import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("imageId") var imageId: String? = null,
    @SerializedName("imageName") var imageName: String? = null,
    @SerializedName("imagePath") var imagePath: String? = null,
    @SerializedName("imageUri") var imageUri: Uri? = null,
    @SerializedName("albumId") var albumId: String? = null,
    @SerializedName("selected") var selected: Boolean = false
)