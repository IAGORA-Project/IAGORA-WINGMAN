package com.iagora.wingman.core.source.local.model


import android.net.Uri
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageModel(
    @SerializedName("imageName") var imageName: String? = null,
    @SerializedName("imagePath") var imagePath: String? = null,
    @SerializedName("imageUri") var imageUri: Uri? = null,
    @SerializedName("selected") var selected: Boolean = false,
): Parcelable
