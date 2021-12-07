package com.iagora.wingman.app.ui.pasar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iagora.wingman.app.utils.EventWrapper
import com.iagora.wingman.core.source.remote.api_handle.pasar.PasarRepository
import com.iagora.wingman.core.source.remote.body.AddProductBody
import com.iagora.wingman.core.source.remote.response.*
import com.iagora.wingman.helper.Resource
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PasarViewModel(
    private val pasarRepository: PasarRepository,
): ViewModel() {

    val listPasar: MutableLiveData<EventWrapper<Resource<ResGetListPasar>>> = MutableLiveData()
    val listProductPasar: MutableLiveData<EventWrapper<Resource<ResGetListProductPasar>>> = MutableLiveData()
    val listTypeAndCategory: MutableLiveData<EventWrapper<Resource<ResGetListTypeAndCategory>>> = MutableLiveData()
    val addProduct: MutableLiveData<EventWrapper<Resource<ResAddProduct>>> = MutableLiveData()
    val addphoto: MutableLiveData<EventWrapper<Resource<ResAddPhoto>>> = MutableLiveData()


    fun vmGetListPasar(): LiveData<EventWrapper<Resource<ResGetListPasar>>> {
        listPasar.postValue(EventWrapper(Resource.loading("true", null)))

        try {
            pasarRepository.getListPasar().enqueue(object : Callback<ResGetListPasar> {
                override fun onResponse(
                    call: Call<ResGetListPasar>,
                    response: Response<ResGetListPasar>,
                ) {
                    val body = response.body()

                    if (response.code() == 200) {
                        Log.d("berhasilllll", "$body")
                        listPasar.postValue(EventWrapper(Resource.success(body)))
                    } else {
                        val json = JSONObject(response.errorBody().toString())
                        Log.d("ERRORNIH", "$json")
                        listPasar.postValue(EventWrapper(Resource.error("Gagal mendapatkan data.",
                            null)))
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


        listProductPasar.postValue(EventWrapper(Resource.loading("true", null)))

        try {
            pasarRepository.getListProductPasar(idPasar)
                .enqueue(object : Callback<ResGetListProductPasar> {
                    override fun onResponse(
                        call: Call<ResGetListProductPasar>,
                        response: Response<ResGetListProductPasar>,
                    ) {
                        val body = response.body()

                        if (response.code() == 200) {
                            Log.d("berhasilllllProducctt", "$body")
                            listProductPasar.postValue(EventWrapper(Resource.success(body)))
                        } else {
                            val json = JSONObject(response.errorBody().toString())
                            Log.d("ERRORNIH", "$json")
                            listProductPasar.postValue(EventWrapper(Resource.error("Gagal mendapatkan data.",
                                null)))
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
        listTypeAndCategory.postValue(EventWrapper(Resource.loading("true", null)))

        try {
            pasarRepository.getListTypeAndCategory()
                .enqueue(object : Callback<ResGetListTypeAndCategory> {
                    override fun onResponse(
                        call: Call<ResGetListTypeAndCategory>,
                        response: Response<ResGetListTypeAndCategory>,
                    ) {
                        val body = response.body()

                        if (response.code() == 200) {
                            listTypeAndCategory.postValue(EventWrapper(Resource.success(body)))
                        } else {
                            val json = JSONObject(response.errorBody().toString())
                            listTypeAndCategory.postValue(EventWrapper(Resource.error("Gagal mendapatkan data.",
                                null)))
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

        addProduct.postValue(EventWrapper(Resource.loading("true", null)))

        try {
            pasarRepository.postAddProduct(addProductBody)
                .enqueue(object : Callback<ResAddProduct> {
                    override fun onResponse(
                        call: Call<ResAddProduct>,
                        response: Response<ResAddProduct>,
                    ) {
                        val body = response.body()

                        if (response.code() == 200) {
                            addProduct.postValue(EventWrapper(Resource.success(body)))
                        } else {
                            val json = JSONObject(response.errorBody().toString())
                            println("jkdfjdfh $json")
                            addProduct.postValue(EventWrapper(Resource.error("Gagal Menambahkan Produk.",
                                null)))
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
                    val json = JSONObject(response.errorBody().toString())
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