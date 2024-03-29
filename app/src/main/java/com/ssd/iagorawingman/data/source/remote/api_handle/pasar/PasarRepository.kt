package com.ssd.iagorawingman.data.source.remote.api_handle.pasar

import com.ssd.iagorawingman.data.source.remote.body.AddProductBody
import com.ssd.iagorawingman.data.source.remote.response.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call

class PasarRepository(
    private val pasarApi: PasarApi
): PasarDataSource {

    override fun getListPasar(token: String): Call<ResGetListPasar> {
        return pasarApi.get_listPasar("Bearer $token")
    }

    override fun getListProductPasar(token: String, idPasar: String): Call<ResGetListProductPasar> {
        return pasarApi.get_listProductPasar("Bearer $token", idPasar)
    }

    override fun getListTypeAndCategory(token: String): Call<ResGetListTypeAndCategory> {
        return pasarApi.get_listTypeAndCategory("Bearer $token")
    }

    override fun postAddProduct(token: String, addProductBody: AddProductBody): Call<ResAddProduct> {
        val idPasar: RequestBody = addProductBody.idPasar!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val productName: RequestBody = addProductBody.namaProduk!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val itemCategories: RequestBody = addProductBody.itemCategories!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val itemType: RequestBody = addProductBody.itemType!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val variant = addProductBody.variant
        val desc: RequestBody = addProductBody.desc!!.toRequestBody("text/plain".toMediaTypeOrNull())


        return pasarApi.post_addProduct(
            "Bearer $token",
            addProductBody.image,
            idPasar,
            productName,
            itemCategories,
            itemType,
            variant,
            desc
        )
    }

    override fun postAddPhoto(photo: ArrayList<MultipartBody.Part>): Call<ResAddPhoto> {
        return pasarApi.post_addPhoto(photo)
    }
}