package com.iagora.wingman.app.main_menu.presentation.home

import com.iagora.wingman.app.main_menu.domain.model.response.WingmanInfo

data class WingmanState(
    val isLoading: Boolean = true,
    val wingmanInfo: WingmanInfo? = null
)
