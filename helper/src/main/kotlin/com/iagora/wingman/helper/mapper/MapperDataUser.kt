package com.iagora.wingman.helper.mapper

import com.iagora.wingman.helper.data.remote.RemDataUser
import com.iagora.wingman.helper.model.DataUser

object MapperDataUser {

    fun RemDataUser?.toModel() =
        DataUser(
            this?.fullName ?: "",
            this?.idUser ?: "",
            this?.imgProfile ?: "",
            this?.phoneNumber ?: ""
        )

    fun DataUser?.toRem() =
        RemDataUser(
            this?.fullName ?: "",
            this?.idUser ?: "",
            this?.imgProfile ?: "",
            this?.phoneNumber ?: ""
        )
}