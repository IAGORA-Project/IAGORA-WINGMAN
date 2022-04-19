package com.iagora.wingman.app.auth.data.repository

import com.google.gson.GsonBuilder
import com.iagora.wingman.app.R
import com.iagora.wingman.app.auth.data.remote.AuthApi
import com.iagora.wingman.app.auth.data.remote.request.*
import com.iagora.wingman.app.auth.domain.repository.IAuthRepository
import com.iagora.wingman.core.data.session.SessionManager
import com.iagora.wingman.core.data.util.HeaderNames.SESSID
import com.iagora.wingman.core.domain.usecase.ISavePref
import com.iagora.wingman.core.domain.util.KEYPref
import com.iagora.wingman.core.util.ErrorResponse
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.SimpleResource
import com.iagora.wingman.core.util.UiText
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class AuthRepository(
    private val api: AuthApi,
    private val sessionManager: SessionManager,
    private val savePref: ISavePref
) : IAuthRepository {

    override suspend fun requestOTP(phoneNumber: String): SimpleResource {
        val request = OtpReq(phoneNumber)
        return try {
            val response = api.otp(
                request = request
            )
            if (response.isSuccess()) {
                Resource.SuccessMessage(UiText.DynamicString(response.message!!))
            } else {
                Resource.Error(UiText.DynamicString("Error occurred state : ${response.message}"))
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

    override suspend fun verifyOtp(phoneNumber: String, otpCode: String): SimpleResource {
        val request = VerifyOtpReq(phoneNumber, otpCode)
        return try {
            val response = api.verifyOtp(request)
            if (response.isSuccessful) {
                val res = response.body()!!
                savePref(KEYPref.WINGMANID, res.result?.userId!!)
                savePref(KEYPref.TOKEN, "Bearer ${res.result?.refreshToken!!}")
                Resource.SuccessMessage(uiText = UiText.DynamicString(res.message!!))
            } else {
                Resource.Error(
                    uiText = UiText.DynamicString(getMessageException(response.errorBody()!!).message!!)
                )
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

    override suspend fun getAccesToken(token: String): SimpleResource {
        return try {
            val response = api.getAccessToken(token)
            if (response.isSuccessful) {
                val res = response.body()!!

                savePref(KEYPref.ACCESSTOKEN, "Bearer ${res.result?.accessToken!!}")
                savePref(KEYPref.WINGMANID, res.result?.userId!!)

                Resource.SuccessMessage(uiText = UiText.DynamicString(res.message!!))

            } else {
                Resource.Error(
                    uiText = UiText.DynamicString(getMessageException(response.errorBody()!!).message!!)
                )
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

    override suspend fun registrationDetail(req: RegistrationReq): SimpleResource {
        return try {
            val request = api.registerDetail(
                sessionManager.getFromPreference(KEYPref.ACCESSTOKEN),
                sessionManager.getFromPreference(KEYPref.WINGMANID),
                req
            )
            if (request.isSuccessful) {
                Resource.SuccessMessage(uiText = UiText.DynamicString(request.body()?.message!!))
            } else {
                Resource.Error(
                    uiText = UiText.DynamicString(getMessageException(request.errorBody()!!).result?.get(0)!!.msg!!)
                )
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

    override suspend fun registrationComplate(
        ktp: MultipartBody.Part,
        skck: MultipartBody.Part,
        map: Map<String, RequestBody>
    ): SimpleResource {
        return try {
            val request = api.registerComplete(
                sessionManager.getFromPreference(KEYPref.ACCESSTOKEN),
                sessionManager.getFromPreference(KEYPref.WINGMANID),
                ktp,
                skck,
                map
            )
            if (request.isSuccessful) {
                Resource.SuccessMessage(uiText = UiText.DynamicString(request.body()?.message!!))
            } else {
                Resource.Error(
                    uiText = UiText.DynamicString(getMessageException(request.errorBody()!!).result?.get(0)!!.msg!!)
                )
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
    private fun getMessageException(body: ResponseBody): ErrorResponse {
        val gson = GsonBuilder().create()
        return gson.fromJson(body.string(), ErrorResponse::class.java)!!
    }

}