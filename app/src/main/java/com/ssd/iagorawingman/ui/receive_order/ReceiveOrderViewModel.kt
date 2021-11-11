package com.ssd.iagorawingman.ui.receive_order

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssd.iagorawingman.BuildConfig
import com.ssd.iagorawingman.data.source.local.shared_handle.auth.SharedAuthRepository
import com.ssd.iagorawingman.data.source.remote.api_handle.receive_order.ReceiveOrderRepository
import com.ssd.iagorawingman.data.source.remote.body.ReceiveOrderBody
import com.ssd.iagorawingman.data.source.remote.response.ResAcceptedOrder
import com.ssd.iagorawingman.utils.EventWrapper
import com.ssd.iagorawingman.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReceiveOrderViewModel(
    private val receiveOrderRepository: ReceiveOrderRepository,
    private val sharedAuthRepository: SharedAuthRepository
): ViewModel() {

    val receiveOrderAcceptedResult: MutableLiveData<EventWrapper<Resource<ResAcceptedOrder>>> = MutableLiveData()
    val receiveOrderCancelledResult: MutableLiveData<EventWrapper<Resource<ResAcceptedOrder>>> = MutableLiveData()

    fun vmAcceptedReceiverOrder(receiveOrderBody: ReceiveOrderBody): LiveData<EventWrapper<Resource<ResAcceptedOrder>>>{
        val token = sharedAuthRepository.getAuth(BuildConfig.KEY_SHARED_PREFERENCE_AUTH)
        receiveOrderAcceptedResult.postValue(EventWrapper(Resource.loading("true", null)))

        receiveOrderRepository.postAcceptedOrder(token?.success?.token!!, receiveOrderBody).enqueue(object : Callback<ResAcceptedOrder> {
            override fun onResponse(call: Call<ResAcceptedOrder>, response: Response<ResAcceptedOrder>) {
                val body = response.body()

                if(response.code() == 200){
                    Log.d("berhasilllll", "$body")
                    receiveOrderAcceptedResult.postValue(EventWrapper(Resource.success(body)))
                }else{
                    receiveOrderAcceptedResult.postValue(EventWrapper(Resource.error("Terjadi Kesalahan.", null)))
                }
            }

            override fun onFailure(call: Call<ResAcceptedOrder>, t: Throwable) {
                receiveOrderAcceptedResult.postValue(EventWrapper(Resource.error("Gagal mengirim data.", null)))
            }

        })

        return receiveOrderAcceptedResult
    }


    fun vmCancelledReceiverOrder(receiveOrderBody: ReceiveOrderBody): LiveData<EventWrapper<Resource<ResAcceptedOrder>>>{
        val token = sharedAuthRepository.getAuth(BuildConfig.KEY_SHARED_PREFERENCE_AUTH)
        receiveOrderCancelledResult.postValue(EventWrapper(Resource.loading("true", null)))

        receiveOrderRepository.postCancelledOrder(token?.success?.token!!, receiveOrderBody).enqueue(object : Callback<ResAcceptedOrder> {
            override fun onResponse(call: Call<ResAcceptedOrder>, response: Response<ResAcceptedOrder>) {
                val body = response.body()

                if(response.code() == 200){
                    Log.d("berhasilllllCENCELL", "$body")
                    receiveOrderCancelledResult.postValue(EventWrapper(Resource.success(body)))
                }else{
                    receiveOrderCancelledResult.postValue(EventWrapper(Resource.error("Gagal mengirim data.", null)))
                }
            }

            override fun onFailure(call: Call<ResAcceptedOrder>, t: Throwable) {
                receiveOrderCancelledResult.postValue(EventWrapper(Resource.error("Terjadi Kesalahan.", null)))
            }
        })

        return receiveOrderCancelledResult
    }
}