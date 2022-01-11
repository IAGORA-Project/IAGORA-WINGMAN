package com.iagora.wingman.app.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.iagora.wingman.app.utils.EventWrapper
import com.iagora.wingman.core.BuildConfig.*
import com.iagora.wingman.core.util.ChCrypto
import com.iagora.wingman.core.source.local.shared_handle.auth.SharedAuthRepository
import com.iagora.wingman.core.source.local.shared_handle.device_token.SharedDeviceTokenRepository
import com.iagora.wingman.core.source.remote.api_handle.auth.AuthReposiroty
import com.iagora.wingman.core.source.remote.body.LoginBody
import com.iagora.wingman.core.source.remote.response.ResLogin
import com.iagora.wingman.helper.Resource
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel(
    private val authReposiroty: AuthReposiroty,
    private val sharedAuthRepository: SharedAuthRepository,
    private val sharedDeviceTokenRepository: SharedDeviceTokenRepository,
): ViewModel() {
    val loginResult: MutableLiveData<EventWrapper<Resource<ResLogin>>> = MutableLiveData()

    private fun saveAuthUserLogin(data: ResLogin?){
        if(data != null){
            val toJson = Gson().toJson(data)
            val EncryptData = ChCrypto.aesEncrypt(toJson, KEY_CRYPTO_AUTH)
            sharedAuthRepository.saveAuth(KEY_SHARED_PREFERENCE_AUTH, EncryptData)
        }
    }

    fun saveDeviceToken(device_token: String) {
        val get_device_token = sharedDeviceTokenRepository.getDeviceToken(KEY_SHARED_PREFERENCE_DT)

        if(get_device_token.isNullOrEmpty()){
            println("NULLLLLLLLLLLLL")
            val EncryptData = ChCrypto.aesEncrypt(device_token, KEY_CRYPTO_DT)
            sharedDeviceTokenRepository.saveDeviceToken(KEY_SHARED_PREFERENCE_DT, EncryptData)
        }else{
            println("drgdgdgdfgdfg $get_device_token")
        }
    }

    fun vmSplashScreen(): MutableLiveData<EventWrapper<Resource<ResLogin>>> {
        val token = sharedAuthRepository.getAuth(KEY_SHARED_PREFERENCE_AUTH)

        println("KEY_SHARED_PREFERENCE_AUTH: ${token?.success?.token}")

        if(token != null){
            loginResult.postValue(EventWrapper(Resource.success(token)))
        }else{
            loginResult.postValue(EventWrapper(Resource.error("Tidak ada data", null)))
        }

        return loginResult
    }


    fun vmLogin(loginBody: LoginBody): LiveData<EventWrapper<Resource<ResLogin>>>{
        loginResult.postValue(EventWrapper(Resource.loading("true", null)))

        authReposiroty.postLogin(loginBody).enqueue(object : Callback<ResLogin> {
            override fun onResponse(call: Call<ResLogin>, response: Response<ResLogin>) {
                val body = response.body()

                if (response.code() == 200){
                    saveAuthUserLogin(body!!)
                    loginResult.postValue(EventWrapper(Resource.success(body)))
                }else{
                    val json = JSONObject(response.errorBody().toString())
                    loginResult.postValue(EventWrapper(Resource.error("ERROR-LOGIN", null)))
                }
            }

            override fun onFailure(call: Call<ResLogin>, t: Throwable) {
                loginResult.postValue(EventWrapper(Resource.error("Terjadi kesalahan.", null)))
            }
        })

        return loginResult
    }
}