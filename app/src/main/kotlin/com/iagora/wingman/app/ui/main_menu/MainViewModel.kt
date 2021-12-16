package com.iagora.wingman.app.ui.main_menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.iagora.wingman.app.utils.EventWrapper
import com.iagora.wingman.core.BuildConfig
import com.iagora.wingman.core.ChCrypto
import com.iagora.wingman.core.source.local.shared_handle.wingman_info.SharedWingmanInfoRepository
import com.iagora.wingman.core.source.remote.api_handle.main_menu.MainMenuRepository
import com.iagora.wingman.core.source.remote.response.ResGetWingmanInfo
import com.iagora.wingman.helper.Resource
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val mainMenuRepository: MainMenuRepository,
    private val sharedWingmanInfoRepository: SharedWingmanInfoRepository,
) : ViewModel() {

    val wingmanInfo: MutableLiveData<EventWrapper<Resource<ResGetWingmanInfo>>> = MutableLiveData()
    val getSharedwingmanInfo: MutableLiveData<ResGetWingmanInfo> = MutableLiveData()


    private fun saveWingmanInfo(data: ResGetWingmanInfo?) {
        println("SKJHSJKSHJKSHJSJH $data")
        if (data != null) {
            val toJson = Gson().toJson(data)
            val EncryptData = ChCrypto.aesEncrypt(toJson, BuildConfig.KEY_CRYPTO_AUTH)
            sharedWingmanInfoRepository.saveWingmanInfo(BuildConfig.KEY_SHARED_PREFERENCE_WINGMAN_INFO,
                EncryptData)
        }
        getSharedwingmanInfo.value = data

    }


    fun vmGetWingmanInfo(): LiveData<EventWrapper<Resource<ResGetWingmanInfo>>> {
        wingmanInfo.postValue(EventWrapper(Resource.loading("true", null)))

        try {
            mainMenuRepository.getWingmanInfo().enqueue(object: Callback<ResGetWingmanInfo> {
                override fun onResponse(call: Call<ResGetWingmanInfo>, response: Response<ResGetWingmanInfo>) {
                    val body = response.body()

                    if(response.code() == 200){
                        Log.d("berhasilllll", "$body")
                        wingmanInfo.postValue(EventWrapper(Resource.success(body)))
                        saveWingmanInfo(body)
                    }else{
                        val json = JSONObject(response.errorBody()?.string())
                        Log.d("ERRORNIH", "$json")
                        wingmanInfo.postValue(EventWrapper(Resource.error("Gagal mendapatkan data.", null)))
                    }
                }

                override fun onFailure(call: Call<ResGetWingmanInfo>, t: Throwable) {
                    wingmanInfo.postValue(EventWrapper(Resource.error("Terjadi Kesalahan.", null)))
                }

            })
        }catch (e: Exception){
            wingmanInfo.postValue(EventWrapper(Resource.error("Unauthenticated.", null)))

        }
        return wingmanInfo
    }


}