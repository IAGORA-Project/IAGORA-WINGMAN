package com.iagora.wingman.core.data.mapper

import com.iagora.wingman.core.data.remote.dto.DataUserDto
import com.iagora.wingman.core.domain.model.dto.DataUser

object MapperDataUser {

    fun DataUserDto?.toModel() =
        DataUser(
            this?.fullName ?: "",
            this?.idUser ?: "",
            this?.imgProfile ?: "",
            this?.phoneNumber ?: ""
        )

    fun DataUser?.toRem() =
        DataUserDto(
            this?.fullName ?: "",
            this?.idUser ?: "",
            this?.imgProfile ?: "",
            this?.phoneNumber ?: ""
        )
}