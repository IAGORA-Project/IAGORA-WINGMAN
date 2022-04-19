package com.iagora.wingman.market.presentation.add_product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.core.data.model.market.remote.ReqAddNewProduct
import com.iagora.wingman.core.presentation.util.UiEvent
import com.iagora.wingman.core.util.Event
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.UiText
import com.iagora.wingman.market.domain.usecase.IGetListTypeAndCategory
import com.iagora.wingman.market.domain.usecase.IPostAddNewProduct
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import timber.log.Timber

class AddProductViewModel(
    private val getIGetListTypeAndCategory: IGetListTypeAndCategory,
    private val getIPostAddNewProduct: IPostAddNewProduct
) : ViewModel() {

    private val _listTypeAndCategory = MutableStateFlow(ListTypeAndCategoryState())
    val listTypeAndCategory = _listTypeAndCategory.asStateFlow()

    private val _eventFLow = MutableSharedFlow<Event>()
    val eventFlow = _eventFLow.asSharedFlow()


    init {
        loadListTypeAndCategory()
    }

    fun postNewProduct(map: HashMap<String, RequestBody>, image: MultipartBody.Part){
        viewModelScope.launch {
            val result = getIPostAddNewProduct(map, image)
            when(result){
                is Resource.Success -> {
                    Timber.tag("yy").d("postNewProduct: ${result.data}")
                }

                is Resource.Error ->{
                    _eventFLow.emit(
                        UiEvent.CreateMessage(
                            result.uiText ?: UiText.unknownError()
                        )
                    )
                }
                else -> {}
            }
        }
    }

    private fun loadListTypeAndCategory() =
        viewModelScope.launch {

            when (val result = getIGetListTypeAndCategory()) {
                is Resource.Success -> {
                    _listTypeAndCategory.emit(
                        listTypeAndCategory.value.copy(
                            isLoading = false,
                            data = result.data
                        )
                    )
                }

                is Resource.Error -> {
                    _listTypeAndCategory.emit(
                        listTypeAndCategory.value.copy(
                            isLoading = false,
                        )
                    )
                }
            }
        }

}