package com.iagora.wingman.market.features.add_product

import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import com.iagora.wingman.commons.ui.base.BaseFragment
import com.iagora.wingman.commons.ui.extensions.collectWhenCreated
import com.iagora.wingman.commons.ui.extensions.collectWhenStarted
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.helper.set
import com.iagora.wingman.market.features.add_product.databinding.FragmentAddProductBinding
import com.iagora.wingman.market.helper.model.response.ListTypeAndCategory
import com.iagora.wingman.market.viewmodels.AddProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProductFragment : BaseFragment<FragmentAddProductBinding>(R.layout.fragment_add_product,
    { FragmentAddProductBinding.bind(it) }) {

    private val viewModel: AddProductViewModel by viewModel()
    private val _listTempVariant = mutableListOf<TempListVariant>()
    private var idVariant = 0
    private val listTempVariant: List<TempListVariant> get() = _listTempVariant

    private val adapter by lazy {
        AddProductAdapter()
    }

    override fun setTitleToolbar(supportActionBar: ActionBar?) {
        super.setTitleToolbar(supportActionBar)
        supportActionBar?.title = resources.getString(R.string.text_add_product)
    }

    override fun setView() {

        viewModel.vmData.collectWhenCreated(this) { data ->
            if (data == null) {
                subscribeToViewModel()
            } else {
                handleUISuccess(data.success)
            }
        }
    }

    override fun setAdapter() {
        super.setAdapter()

        adapter.apply { submitList(listTempVariant) }
            .setonClickDeleteItem { item ->
                _listTempVariant.remove(item)
            }
        binding.rvListVariantPrice.adapter = adapter
    }


    private fun subscribeToViewModel() {
        viewModel.vmGetListTypeCategory.collectWhenStarted(this) { res ->
            handleUI(res)
        }
    }

    private fun handleUI(res: Resource<ListTypeAndCategory>) {
        res.apply {
            set(
                error = { setVisibilityLayout(false, error = false) },
                success = {
                    viewModel.setData(data)
                    handleUISuccess(data?.success)
                },
                loading = { setVisibilityLayout(false) }
            )
        }

        binding.containerBtn.btnPrimary.isEnabled = true

        binding.containerBtn.btnPrimary.setOnClickListener {
            Log.e("LIST", listTempVariant.toString())
        }
    }

    private fun handleUISuccess(data: ListTypeAndCategory.Success?) {
        setVisibilityLayout(true)
        val listUnit = requireActivity().resources.getStringArray(R.array.unit).toList()

        binding.apply {
            data?.apply {
                dropdownChildCategoryProduct.setDropDown(categories.map { it.categoryName })
                dropdownChildTypeProduct.setDropDown(type.map { it.typeName })
            }

            dropdownChildSatuan.apply { setDropDown(listUnit) }
                .setOnItemClickListener { _, _, position, _ ->
                    val unit = listUnit[position]
                    val itemCount = adapter.itemCount
                    initVariantPrice(itemCount, unit)
                    setVisibilityVariant(unit)
                }
        }
    }

    private fun initVariantPrice(itemCount: Int, unit: String) {
        val firstItemVariant = TempListVariant(0, unit)
        if (itemCount == 0) {
            addListVariant(unit)
        } else {
            if (!_listTempVariant.any { it.unit == unit }) {
                _listTempVariant[0] = firstItemVariant
                adapter.notifyItemChanged(0)
            }

            if (itemCount >= 1 && unit == resources.getString(R.string.text_pcs)) {
                idVariant = 0
                _listTempVariant.removeAll { it.id > 1 }
                adapter.notifyItemRangeRemoved(1, itemCount - 1)
            }
        }

        binding.tvAddVariantPrice.setOnClickListener {
            idVariant += 1
            addListVariant(unit)
        }
    }


    private fun setVisibilityVariant(unit: String) {
        binding.apply {
            tvAddVariantPrice.isVisible = unit == resources.getString(R.string.text_gram)
            tvTitleVariant.isVisible = true
            rvListVariantPrice.isVisible = true
        }
    }

    private fun addListVariant(unit: String) {
        _listTempVariant.add(TempListVariant(idVariant, unit))
        adapter.notifyItemInserted(idVariant)
    }


    private fun AutoCompleteTextView.setDropDown(listString: List<String>) {
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_text, listString)
        setAdapter(arrayAdapter)
    }

    private fun setVisibilityLayout(isVisible: Boolean, error: Boolean = false) {
        binding.apply {
            if (error) {
                containerBtn.root.isVisible = isVisible
                nestedScrollView.isVisible = isVisible
                progressBar.isVisible = isVisible
            } else {
                containerBtn.root.isVisible = isVisible
                nestedScrollView.isVisible = isVisible
                progressBar.isVisible = !isVisible
            }
        }
    }

    companion object {
        data class TempListVariant(
            val id: Int,
            val unit: String,
            var price: Long? = 0,
            var variant: Long? = 0,
        )
    }

}