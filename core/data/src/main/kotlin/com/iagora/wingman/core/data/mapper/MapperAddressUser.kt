package com.iagora.wingman.core.data.mapper

import com.iagora.wingman.core.data.remote.dto.AddressUserDto
import com.iagora.wingman.core.domain.model.dto.AddressUser


 object MapperAddressUser {

    fun AddressUserDto?.toModel() = AddressUser(
        details = this?.details ?: "",
        fullName = this?.fullName ?: "",
        note = this?.note ?: "",
        phoneNumber = this?.phoneNumber ?: ""
    )

    fun AddressUser?.toRem() = AddressUserDto(
        details = this?.details ?: "",
        fullName = this?.fullName ?: "",
        note = this?.note ?: "",
        phoneNumber = this?.phoneNumber ?: ""
    )

}