package com.iagora.wingman.core.source.local.shared_view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iagora.wingman.core.source.local.model.ImageModel

class SharedViewModel : ViewModel() {

    var imageModelSelected: MutableLiveData<ArrayList<ImageModel>> = MutableLiveData()
    var addProductImageModel: MutableLiveData<ArrayList<ImageModel>> = MutableLiveData()


    fun tempImageSelected(data: ArrayList<ImageModel>?) {
        println("WOKWOKWWOWWW $data")
        imageModelSelected.value = data
    }

    fun addProductImage(data: ArrayList<ImageModel>?) {
        addProductImageModel.value = data
    }

}