package com.iagora.wingman.market.presentation.add_product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.UiText
import com.iagora.wingman.market.domain.usecase.IGetListTypeAndCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddProductViewModel(
    private val getIGetListTypeAndCategory: IGetListTypeAndCategory
) : ViewModel() {

    private val _listTypeAndCategory = MutableStateFlow(ListTypeAndCategoryState())
    val listTypeAndCategory = _listTypeAndCategory.asStateFlow()


    init {
        loadListTypeAndCategory()
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
                    Log.e("ERROR", (result.uiText ?: UiText.unknownError()).toString())
                }
            }
        }

}