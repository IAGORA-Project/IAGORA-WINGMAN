package com.iagora.wingman.core.domain.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataUser(
    val fullName: String,
    val idUser: String,
    val imgProfile: String,
    val phoneNumber: String,
): Parcelable