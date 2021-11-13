package com.ssd.iagorawingman.ui.main_menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.ssd.iagorawingman.BuildConfig
import com.ssd.iagorawingman.data.source.local.shared_handle.auth.SharedAuthRepository
import com.ssd.iagorawingman.data.source.local.shared_handle.wingman_info.SharedWingmanInfoRepository
import com.ssd.iagorawingman.data.source.remote.api_handle.main_menu.MainMenuRepository
import com.ssd.iagorawingman.data.source.remote.response.ResGetWingmanInfo
import com.ssd.iagorawingman.data.source.remote.response.ResLogin
import com.ssd.iagorawingman.utils.ChCrypto
import com.ssd.iagorawingman.utils.EventWrapper
import com.ssd.iagorawingman.utils.Resource
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val mainMenuRepository: MainMenuRepository,
    private val sharedWingmanInfoRepository: SharedWingmanInfoRepository,
): ViewModel() {

    val wingmanInfo: MutableLiveData<EventWrapper<Resource<ResGetWingmanInfo>>> = MutableLiveData()
    val getSharedwingmanInfo: MutableLiveData<ResGetWingmanInfo> = MutableLiveData()


    private fun saveWingmanInfo(data: ResGetWingmanInfo?){
        println("SKJHSJKSHJKSHJSJH $data")
        if(data != null){
            val toJson = Gson().toJson(data)
            val EncryptData = ChCrypto.aesEncrypt(toJson, BuildConfig.KEY_CRYPTO_AUTH)
            sharedWingmanInfoRepository.saveWingmanInfo(BuildConfig.KEY_SHARED_PREFERENCE_WINGMAN_INFO, EncryptData)
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