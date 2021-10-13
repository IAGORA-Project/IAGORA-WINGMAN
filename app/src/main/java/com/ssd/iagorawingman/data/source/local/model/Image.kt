package com.ssd.iagorawingman.data.source.local.model


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("imageId") var imageId: String? = null,
    @SerializedName("imageName") var imageName: String? = null,
    @SerializedName("imagePath") var imagePath: String? = null,
    @SerializedName("albumId") var albumId: String? = null,
    @SerializedName("selected") var selected: Boolean = false
)