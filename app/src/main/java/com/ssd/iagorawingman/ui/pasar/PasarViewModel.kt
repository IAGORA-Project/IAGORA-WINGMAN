package com.ssd.iagorawingman.ui.pasar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssd.iagorawingman.BuildConfig
import com.ssd.iagorawingman.data.source.local.shared_handle.auth.SharedAuthRepository
import com.ssd.iagorawingman.data.source.remote.api_handle.pasar.PasarRepository
import com.ssd.iagorawingman.data.source.remote.body.AddProductBody
import com.ssd.iagorawingman.data.source.remote.response.*
import com.ssd.iagorawingman.utils.EventWrapper
import com.ssd.iagorawingman.utils.Resource
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PasarViewModel(
    private val pasarRepository: PasarRepository,
    private val sharedAuthRepository: SharedAuthRepository
): ViewModel() {

    val listPasar: MutableLiveData<EventWrapper<Resource<ResGetListPasar>>> = MutableLiveData()
    val listProductPasar: MutableLiveData<EventWrapper<Resource<ResGetListProductPasar>>> = MutableLiveData()
    val listTypeAndCategory: MutableLiveData<EventWrapper<Resource<ResGetListTypeAndCategory>>> = MutableLiveData()
    val addProduct: MutableLiveData<EventWrapper<Resource<ResAddProduct>>> = MutableLiveData()
    val addphoto: MutableLiveData<EventWrapper<Resource<ResAddPhoto>>> = MutableLiveData()


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


    fun vmGetListTypeAndCategory(): LiveData<EventWrapper<Resource<ResGetListTypeAndCategory>>> {
        val token = sharedAuthRepository.getAuth(BuildConfig.KEY_SHARED_PREFERENCE_AUTH)
        listTypeAndCategory.postValue(EventWrapper(Resource.loading("true", null)))

        try {
            pasarRepository.getListTypeAndCategory(token?.success?.token!!).enqueue(object : Callback<ResGetListTypeAndCategory> {
                override fun onResponse(call: Call<ResGetListTypeAndCategory>, response: Response<ResGetListTypeAndCategory>) {
                    val body = response.body()

                    if(response.code() == 200){
                        listTypeAndCategory.postValue(EventWrapper(Resource.success(body)))
                    }else{
                        val json = JSONObject(response.errorBody()?.string())
                        listTypeAndCategory.postValue(EventWrapper(Resource.error("Gagal mendapatkan data.", null)))
                    }
                }

                override fun onFailure(call: Call<ResGetListTypeAndCategory>, t: Throwable) {
                    listTypeAndCategory.postValue(EventWrapper(Resource.error("Terjadi Kesalahan.", null)))
                }
            })

        }catch (e: Exception) {
            listTypeAndCategory.postValue(EventWrapper(Resource.error("Unauthenticated.", null)))
        }

        return listTypeAndCategory
    }


    fun vmAddProduct(addProductBody: AddProductBody): LiveData<EventWrapper<Resource<ResAddProduct>>>{
        val token = sharedAuthRepository.getAuth(BuildConfig.KEY_SHARED_PREFERENCE_AUTH)
        addProduct.postValue(EventWrapper(Resource.loading("true", null)))

        try {
            pasarRepository.postAddProduct(token?.success?.token!!, addProductBody).enqueue(object: Callback<ResAddProduct> {
                override fun onResponse(call: Call<ResAddProduct>, response: Response<ResAddProduct>) {
                    val body = response.body()

                    if(response.code() == 200){
                        addProduct.postValue(EventWrapper(Resource.success(body)))
                    }else{
                        val json = JSONObject(response.errorBody()?.string())
                        println("jkdfjdfh $json")
                        addProduct.postValue(EventWrapper(Resource.error("Gagal Menambahkan Produk.", null)))
                    }
                }

                override fun onFailure(call: Call<ResAddProduct>, t: Throwable) {
                    println("JSJDKDKJDKJDID $t")
                    addProduct.postValue(EventWrapper(Resource.error("Terjadi Kesalahan.", null)))
                }
            })

        }catch (e: Exception){
            addProduct.postValue(EventWrapper(Resource.error("Unauthenticated.", null)))
        }

        return addProduct
    }


    fun vmAddPhoto(photo: ArrayList<MultipartBody.Part>): LiveData<EventWrapper<Resource<ResAddPhoto>>> {
        addphoto.postValue(EventWrapper(Resource.loading("true", null)))

        println("KIRIMAPA $photo")
        pasarRepository.postAddPhoto(photo).enqueue(object : Callback<ResAddPhoto> {
            override fun onResponse(call: Call<ResAddPhoto>, response: Response<ResAddPhoto>) {
                val body = response.body()

               Log.d("OKEEEMANTAP", "")

                println("DDHJDHDJDH $response")

                if (response.code() == 200) {
                    Log.d("berhasilllllADDPHOTO", "$body")
                    addphoto.postValue(EventWrapper(Resource.success(body)))
                }else{
                    val json = JSONObject(response.errorBody()?.string())
                    Log.d("ERRORNIHAddphoto", "$json")
                    addphoto.postValue(EventWrapper(Resource.error("Gagal tambah produk", null)))

                }
            }

            override fun onFailure(call: Call<ResAddPhoto>, t: Throwable) {
                Log.d("gagalphoto", "$t")
                addphoto.postValue(EventWrapper(Resource.error("Terjadi Kesalahan.", null)))
            }

        })

        return addphoto
    }
}