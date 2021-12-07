package com.iagora.wingman.app.ui.receive_order

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iagora.wingman.app.utils.EventWrapper
import com.iagora.wingman.core.source.remote.api_handle.receive_order.ReceiveOrderRepository
import com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody
import com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder
import com.iagora.wingman.helper.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReceiveOrderViewModel(
    private val receiveOrderRepository: ReceiveOrderRepository,
): ViewModel() {

    val receiveOrderAcceptedResult: MutableLiveData<EventWrapper<Resource<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder>>> = MutableLiveData()
    val receiveOrderCancelledResult: MutableLiveData<EventWrapper<Resource<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder>>> = MutableLiveData()

    fun vmAcceptedReceiverOrder(receiveOrderBody: com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody): LiveData<EventWrapper<Resource<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder>>>{
        receiveOrderAcceptedResult.postValue(EventWrapper(Resource.loading("true", null)))

        receiveOrderRepository.postAcceptedOrder(receiveOrderBody).enqueue(object : Callback<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder> {
            override fun onResponse(call: Call<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder>, response: Response<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder>) {
                val body = response.body()

                if(response.code() == 200){
                    Log.d("berhasilllll", "$body")
                    receiveOrderAcceptedResult.postValue(EventWrapper(Resource.success(body)))
                }else{
                    receiveOrderAcceptedResult.postValue(EventWrapper(Resource.error("Terjadi Kesalahan.", null)))
                }
            }

            override fun onFailure(call: Call<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder>, t: Throwable) {
                receiveOrderAcceptedResult.postValue(EventWrapper(Resource.error("Gagal mengirim data.", null)))
            }

        })

        return receiveOrderAcceptedResult
    }


    fun vmCancelledReceiverOrder(receiveOrderBody: com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody): LiveData<EventWrapper<Resource<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder>>>{
        receiveOrderCancelledResult.postValue(EventWrapper(Resource.loading("true", null)))

        receiveOrderRepository.postCancelledOrder(receiveOrderBody).enqueue(object : Callback<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder> {
            override fun onResponse(call: Call<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder>, response: Response<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder>) {
                val body = response.body()

                if(response.code() == 200){
                    Log.d("berhasilllllCENCELL", "$body")
                    receiveOrderCancelledResult.postValue(EventWrapper(Resource.success(body)))
                }else{
                    receiveOrderCancelledResult.postValue(EventWrapper(Resource.error("Gagal mengirim data.", null)))
                }
            }

            override fun onFailure(call: Call<com.iagora.wingman.receive_order.core.data.remote.response.ResAcceptedOrder>, t: Throwable) {
                receiveOrderCancelledResult.postValue(EventWrapper(Resource.error("Terjadi Kesalahan.", null)))
            }
        })

        return receiveOrderCancelledResult
    }
}