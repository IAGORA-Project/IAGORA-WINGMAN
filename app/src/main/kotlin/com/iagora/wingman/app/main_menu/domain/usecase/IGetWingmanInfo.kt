package com.iagora.wingman.app.main_menu.domain.usecase

import com.iagora.wingman.app.main_menu.domain.model.response.WingmanInfo
import com.iagora.wingman.core.util.Resource

interface IGetWingmanInfo {
    suspend operator fun invoke(): Resource<WingmanInfo>
}