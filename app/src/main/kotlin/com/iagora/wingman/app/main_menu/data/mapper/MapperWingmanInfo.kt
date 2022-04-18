package com.iagora.wingman.app.main_menu.data.mapper

import com.iagora.wingman.app.main_menu.data.remote.response.ResWingmanInfo
import com.iagora.wingman.app.main_menu.domain.model.response.WingmanInfo

object MapperWingmanInfo {
    fun ResWingmanInfo.toModel() =
        WingmanInfo(status, success.toModel())

    private fun ResWingmanInfo.Success.toModel() = WingmanInfo.Success(
        balance = balance ?: 0,
        email = email ?: "not-found",
        fullName = fullName ?: "unknown",
        idKol = idKol,
        imgProfile = imgProfile ?: "",
        phoneNumber = imgProfile ?: "-",
        statusActive = statusActive
    )
}