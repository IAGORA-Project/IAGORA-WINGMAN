package com.iagora.wingman.core.mapper

import com.iagora.wingman.core.data.remote.RemAddressUser
import com.iagora.wingman.helper.model.AddressUser

object MapperAddressUser {

    fun RemAddressUser?.toModel() = AddressUser(
        details = this?.details ?: "",
        fullName = this?.fullName ?: "",
        note = this?.note ?: "",
        phoneNumber = this?.phoneNumber ?: ""
    )

    fun AddressUser?.toRem() = RemAddressUser(
        details = this?.details ?: "",
        fullName = this?.fullName ?: "",
        note = this?.note ?: "",
        phoneNumber = this?.phoneNumber ?: ""
    )

}