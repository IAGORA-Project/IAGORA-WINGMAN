package com.ssd.iagorawingman.ui.pasar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssd.iagorawingman.BuildConfig
import com.ssd.iagorawingman.data.source.local.shared_handle.auth.SharedAuthRepository
import com.ssd.iagorawingman.data.source.remote.api_handle.pasar.PasarRepository
import com.ssd.iagorawingman.data.source.remote.response.ResGetListPasar
import com.ssd.iagorawingman.data.source.remote.response.ResGetListProductPasar
import com.ssd.iagorawingman.utils.EventWrapper
import com.ssd.iagorawingman.utils.Resource
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class PasarViewModel(
    private val pasarRepository: PasarRepository,
    private val sharedAuthRepository: SharedAuthRepository
): ViewModel() {

    val listPasar: MutableLiveData<EventWrapper<Resource<ResGetListPasar>>> = MutableLiveData()
    val listProductPasar: MutableLiveData<EventWrapper<Resource<ResGetListProductPasar>>> = MutableLiveData()


    fun vmGetListPasar(): LiveData<EventWrapper<Resource<ResGetListPasar>>> {
        val token = sharedAuthRepository.getAuth(BuildConfig.KEY_SHARED_PREFERENCE_AUTH)
        listPasar.postValue(EventWrapper(Resource.loading("true", null)))
        println("cobcobcobcobccb")

        println("tokentoken $token")

        try {
            pasarRepository.getListPasar(token?.success?.token!!).enqueue(object : Callback<ResGetListPasar> {
                override fun onResponse(call: Call<ResGetListPasar>, response: Response<ResGetListPasar>) {
                    val body = response.body()

                    if(response.code() == 200){
                        Log.d("berhasilllll", "$body")
                        listPasar.postValue(EventWrapper(Resource.success(body)))
                    }else{
                        val json = JSONObject(response.errorBody()?.string())
                        Log.d("ERRORNIH", "$json")
                        listPasar.postValue(EventWrapper(Resource.error("Gagal mendapatkan data.", null)))
                    }
                }

                override fun onFailure(call: Call<ResGetListPasar>, t: Throwable) {
                    println("errrorrrrrr $t")

                    listPasar.postValue(EventWrapper(Resource.error("Terjadi Kesalahan.", null)))
                }

            })
        }catch (e: Exception) {
           println("ERRORCATCH")

            listPasar.postValue(EventWrapper(Resource.error("Unauthenticated.", null)))
        }

        return listPasar

    }


    fun vmGetListProductPasar(idPasar: String): LiveData<EventWrapper<Resource<ResGetListProductPasar>>> {
        val token = sharedAuthRepository.getAuth(BuildConfig.KEY_SHARED_PREFERENCE_AUTH)

        listProductPasar.postValue(EventWrapper(Resource.loading("true", null)))

        try {
            pasarRepository.getListProductPasar(token?.success?.token!!, idPasar).enqueue(object : Callback<ResGetListProductPasar> {
                override fun onResponse(call: Call<ResGetListProductPasar>, response: Response<ResGetListProductPasar>) {
                    val body = response.body()

                    if(response.code() == 200){
                        Log.d("berhasilllllProducctt", "$body")
                        listProductPasar.postValue(EventWrapper(Resource.success(body)))
                    }else{
                        val json = JSONObject(response.errorBody()?.string())
                        Log.d("ERRORNIH", "$json")
                        listProductPasar.postValue(EventWrapper(Resource.error("Gagal mendapatkan data.", null)))
                    }
                }

                override fun onFailure(call: Call<ResGetListProductPasar>, t: Throwable) {
                    listProductPasar.postValue(EventWrapper(Resource.error("Terjadi Kesalahan.", null)))
                }
            })

        }catch (e: Exception) {
            listProductPasar.postValue(EventWrapper(Resource.error("Unauthenticated.", null)))
        }

        return listProductPasar
    }

}