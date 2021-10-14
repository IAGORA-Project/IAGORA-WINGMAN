package com.ssd.iagora_user.data.source.local.shared_view_model

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssd.iagorawingman.data.source.local.model.Image
import com.ssd.iagorawingman.data.source.local.model.ImageTakeCamera
import java.io.File

class SharedViewModel(

): ViewModel() {

    var imageSelected: MutableLiveData<ArrayList<Image>> = MutableLiveData()
    var imageSelected2: MutableLiveData<ArrayList<ImageTakeCamera>> = MutableLiveData()


    fun SharedImageSelected(data: ArrayList<Image>?) {
        println("WOKWOKWWOWWW $data")
        imageSelected.value = data
    }

    fun SharedImageSelected2(data: ArrayList<ImageTakeCamera>) {
        println("WOKWOKWWOWWW $data")
        imageSelected2.value = data
    }
}