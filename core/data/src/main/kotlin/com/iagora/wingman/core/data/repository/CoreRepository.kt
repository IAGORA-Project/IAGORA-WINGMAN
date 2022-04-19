package com.iagora.wingman.core.data.repository

import android.content.SharedPreferences
import com.iagora.wingman.core.data.R
import com.iagora.wingman.core.data.remote.CoreAPI
import com.iagora.wingman.core.data.util.PrefSetting.clearData
import com.iagora.wingman.core.domain.repository.ICoreRepository
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.UiText
import retrofit2.HttpException
import java.io.IOException

class CoreRepository(
    private val api: CoreAPI,
) : ICoreRepository {

    override suspend fun getSESSID(): Resource<String> {
        return try {
            val response = api.sessid()
            val sessid = response.headers().get("sessid")
            if (response.isSuccessful && sessid != null && sessid.isNotEmpty()) {
                Resource.Success(sessid)
            } else {
                Resource.Error(UiText.DynamicString("Error occurred state : ${response.message()}"))
            }
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }
}