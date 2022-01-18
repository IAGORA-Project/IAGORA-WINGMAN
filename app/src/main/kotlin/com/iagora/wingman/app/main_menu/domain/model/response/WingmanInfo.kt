package com.iagora.wingman.app.main_menu.domain.model.response


data class WingmanInfo(
    val status: Int,
    val success: Success,
) {
    data class Success(
        val balance: Int,
        val email: String,
        val fullName: String,
        val idKol: String,
        val imgProfile: String,
        val phoneNumber: String,
        val statusActive: Boolean
    )
}