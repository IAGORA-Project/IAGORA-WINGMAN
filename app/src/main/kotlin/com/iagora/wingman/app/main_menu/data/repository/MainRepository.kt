package com.iagora.wingman.app.main_menu.data.repository

import android.content.SharedPreferences
import com.google.gson.Gson
import com.iagora.wingman.app.R
import com.iagora.wingman.app.main_menu.data.mapper.MapperWingmanInfo.toModel
import com.iagora.wingman.app.main_menu.data.remote.MainApi
import com.iagora.wingman.app.main_menu.data.remote.response.ResWingmanInfo
import com.iagora.wingman.app.main_menu.domain.model.response.WingmanInfo
import com.iagora.wingman.app.main_menu.domain.repository.IMainRepository
import com.iagora.wingman.core.data.util.ChCrypto
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.UiText
import retrofit2.HttpException
import java.io.IOException

class MainRepository(
    private val api: MainApi,
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson,
    private val keySharedWingman: String,
    private val keyCryptAuth: String
) : IMainRepository {

    override fun getWingmanInfo(key: String): WingmanInfo? =
        try {
            val getData =
                sharedPreferences.getString(keySharedWingman, "")
                    ?: ""
            val decryptData = ChCrypto.aesDecrypt(getData, keyCryptAuth)
            gson.fromJson(
                decryptData,
                ResWingmanInfo::class.java
            ).toModel()
        } catch (e: Throwable) {
            null
        }


    override suspend fun getNewWingmanInfo(): Resource<WingmanInfo> = try {
        val response = api.getWingmanInfo()
        val encryptData =
            ChCrypto.aesEncrypt(
                gson.toJson(response),
                keyCryptAuth
            )
        sharedPreferences.edit()
            .putString(keySharedWingman, encryptData)
            .apply()
        Resource.Success(response.toModel())
    } catch (e: IOException) {
        Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
    } catch (e: HttpException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.oops_something_went_wrong)
        )
    }
}