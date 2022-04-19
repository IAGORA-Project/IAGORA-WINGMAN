package com.iagora.wingman.market.data.repository

import com.google.gson.GsonBuilder
import com.iagora.wingman.core.data.model.market.data.MarketWithProduct
import com.iagora.wingman.core.util.ErrorResponse
import com.iagora.wingman.core.util.Resource

import com.iagora.wingman.core.util.UiText
import com.iagora.wingman.market.data.R
import com.iagora.wingman.market.data.mapper.MapperListMarket.toModel
import com.iagora.wingman.market.data.mapper.MapperListProduct.toModel
import com.iagora.wingman.market.data.mapper.MapperListTypeAndCategory.toModel
import com.iagora.wingman.market.data.remote.MarketApi
import com.iagora.wingman.core.data.model.market.data.Product
import com.iagora.wingman.core.data.model.market.data.ProductCategory
import com.iagora.wingman.core.data.model.market.remote.ReqAddNewProduct
import com.iagora.wingman.core.data.session.SessionManager
import com.iagora.wingman.core.domain.util.KEYPref
import com.iagora.wingman.market.domain.repository.IMarketRepository
import com.iagora.wingman.market.helper.model.body.AddProduct
import com.iagora.wingman.market.helper.model.response.AddProductFeedBack
import com.iagora.wingman.market.helper.model.response.ListMarket
import com.iagora.wingman.market.helper.model.response.ListProduct
import com.iagora.wingman.market.helper.model.response.ListTypeAndCategory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class MarketRepository(
    private val api: MarketApi,
    private val sessionManager: SessionManager
) : IMarketRepository {

    override suspend fun getListMarket(): Resource<ListMarket> = try {
        val listMarket = api.getListMarket().toModel()
        Resource.Success(listMarket)
    } catch (e: IOException) {
        Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
    } catch (e: HttpException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.oops_something_went_wrong)
        )
    }

    override suspend fun getListProduct(idMarket: String): Resource<MarketWithProduct> = try {
        val listProduct = api.getAllProductByMarketId(idMarket)
        Resource.Success(listProduct.body()?.result)
    } catch (e: IOException) {
        Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
    } catch (e: HttpException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.oops_something_went_wrong)
        )
    }


    override suspend fun getListTypeAndCategory(): Resource<ArrayList<ProductCategory>> = try {
        val listTypeAndCategory = api.getAllCategory()
        if (listTypeAndCategory.isSuccessful) {
            Resource.Success(listTypeAndCategory.body()?.result)
        } else {
            Resource.Error(UiText.DynamicString("Error ${listTypeAndCategory.message()}"))
        }

    } catch (e: IOException) {
        Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
    } catch (e: HttpException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.oops_something_went_wrong)
        )
    }

    override suspend fun postAddNewProduct(
        image: MultipartBody.Part,
        map: Map<String, RequestBody>
    ): Resource<Product> {
      return try {
          val request = api.postNewProduct(sessionManager.getFromPreference(KEYPref.ACCESSTOKEN),image, map)
          if (request.isSuccessful) {
              Resource.Success(request.body()?.result!!)
          } else {
              Resource.Error(
                  uiText = UiText.DynamicString(getMessageException(request.errorBody()!!).message!!)
              )
          }

      }catch (e: IOException) {
          Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
      } catch (e: HttpException) {
          Resource.Error(
              uiText = UiText.StringResource(R.string.oops_something_went_wrong)
          )
      }
    }

    private fun getMessageException(body: ResponseBody): ErrorResponse {
        val gson = GsonBuilder().create()
        return gson.fromJson(body.string(), ErrorResponse::class.java)
    }
}