package com.ssd.iagora_user.data.source.local.shared_view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssd.iagorawingman.data.source.local.model.ImageModel

class SharedViewModel(

): ViewModel() {

    var imageModelSelected: MutableLiveData<ArrayList<ImageModel>> = MutableLiveData()
    var addProductImageModel: MutableLiveData<ArrayList<ImageModel>> = MutableLiveData()


    fun TempImageSelected(data: ArrayList<ImageModel>?) {
        println("WOKWOKWWOWWW $data")
        imageModelSelected.value = data
    }

    fun AddProductImage(data: ArrayList<ImageModel>?) {
        addProductImageModel.value = data
    }

}