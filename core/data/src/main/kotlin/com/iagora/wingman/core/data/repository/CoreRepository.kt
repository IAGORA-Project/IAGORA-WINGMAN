package com.iagora.wingman.core.data.repository

import android.content.SharedPreferences
import com.iagora.wingman.core.data.R
import com.iagora.wingman.core.data.remote.CoreAPI
import com.iagora.wingman.core.data.session.SessionManager
import com.iagora.wingman.core.data.session.SessionManager.Companion.KEY_SESSID
import com.iagora.wingman.core.data.util.PrefSetting.clearData
import com.iagora.wingman.core.domain.repository.ICoreRepository
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.SimpleResource
import com.iagora.wingman.core.util.UiText
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class CoreRepository(
    private val sharedPref: SharedPreferences,
    private val api: CoreAPI,
    private val sessionManager: SessionManager
) : ICoreRepository {
    override fun clearData(key: String): Boolean = sharedPref.clearData(key)

    override suspend fun getSESSID(): SimpleResource {
        return try {
            val response = api.sessid()
            if (response.isSuccessful) {
                val sessid = response.headers().get("sessid")
                if (sessid != null && sessid.isNotEmpty()) {
                    sessionManager.saveToPreference(KEY_SESSID, sessid)
                    Resource.Success(Unit)
                }
                Timber.e(sessid)
                Resource.Error(UiText.DynamicString("sessid is empty"))
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