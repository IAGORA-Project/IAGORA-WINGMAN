package com.iagora.wingman.app.auth.data.repository

import com.iagora.wingman.app.R
import com.iagora.wingman.app.auth.data.remote.AuthApi
import com.iagora.wingman.app.auth.data.remote.request.LoginReq
import com.iagora.wingman.app.auth.data.remote.request.OtpReq
import com.iagora.wingman.app.auth.domain.repository.IAuthRepository
import com.iagora.wingman.core.data.util.HeaderNames.SESSID
import com.iagora.wingman.core.domain.usecase.IGetSESSID
import com.iagora.wingman.core.domain.usecase.ISavePref
import com.iagora.wingman.core.domain.util.KEYPref
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.SimpleResource
import com.iagora.wingman.core.util.UiText
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class AuthRepository(
    private val api: AuthApi,
    private val getSESSID: IGetSESSID,
    private val savePref: ISavePref
) : IAuthRepository {

    override suspend fun requestOTP(phoneNumber: String): SimpleResource {
        val request = OtpReq(phoneNumber)
        return try {
            val sessid = getSESSID().data
            if (sessid != null) {
                val response = api.otp(
                    request = request, sessid = mapOf(
                        SESSID to sessid
                    )
                )
                if (response.isSuccess()) {
                    Resource.Success(Unit)
                } else {
                    Resource.Error(UiText.DynamicString("Error occurred state : ${response.message}"))
                }
            } else {
                Resource.Error(UiText.DynamicString("Error occurred state : ${getSESSID().uiText}"))
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

    override suspend fun requestLogin(
        phoneNumber: String,
        password: String,
    ): SimpleResource {
        val request = LoginReq(phoneNumber, password)
        return try {
            val sessid = getSESSID().data

            if (sessid != null) {
                val response = api.login(
                    request = request, sessid = mapOf(
                        SESSID to sessid
                    )
                )
                val cookie = response.headers().get("Set-Cookie")

                if (response.isSuccessful && cookie != null && savePref(KEYPref.TOKEN, cookie)) {
                    Resource.Success(Unit)
                } else {
                    Timber.e("ERROR LOGIN")
                    Resource.Error(UiText.DynamicString("Error occurred state : ${response.body()?.message}"))
                }
            } else {
                Resource.Error(UiText.DynamicString("Error occurred state : ${getSESSID().uiText}"))
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