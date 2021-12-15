package com.iagora.wingman.core.source.local.model


import android.net.Uri
import com.google.gson.annotations.SerializedName

data class ImageTakeCamera(
    @SerializedName("imageName") var imageName: String? = null,
    @SerializedName("imagePath") var imagePath: String? = null,
    @SerializedName("uri") var uri: Uri? = null,
)