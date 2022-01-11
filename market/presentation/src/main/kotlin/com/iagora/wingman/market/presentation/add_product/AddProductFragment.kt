package com.iagora.wingman.market.presentation.add_product

import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.iagora.wingman.commons.ui.base.BaseFragment
import com.iagora.wingman.commons.ui.extensions.collectWhenStarted
import com.iagora.wingman.market.helper.model.response.ListTypeAndCategory
import com.iagora.wingman.market.presentation.R
import com.iagora.wingman.market.presentation.databinding.FragmentAddProductBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddProductFragment : BaseFragment<FragmentAddProductBinding>(
    R.layout.fragment_add_product,
    { FragmentAddProductBinding.bind(it) }) {

    private val viewModel: AddProductViewModel by viewModel()
    private var idVariant = 0
    private val _listTempVariant = mutableListOf<TempListVariant>()
    private val listTempVariant: List<TempListVariant> get() = _listTempVariant

    lateinit var variantAdapter: VariantAdapter


    override fun setTitleToolbar(supportActionBar: ActionBar?) {
        super.setTitleToolbar(supportActionBar)
        supportActionBar?.title = resources.getString(R.string.text_add_product)
    }

    override fun setView() {

        viewModel.listTypeAndCategory.collectWhenStarted(viewLifecycleOwner) { state ->
            setToggleView(state.isLoading)
            setupUI(state.data?.success)
        }

        setupCreateProduct()
        setupNavigation()
    }

    override fun setAdapter() {
        variantAdapter = VariantAdapter()
        variantAdapter.apply { submitList(listTempVariant) }
            .setonClickDeleteItem { item ->
                _listTempVariant.remove(item)
            }
        binding.rvListVariantPrice.adapter = variantAdapter
    }

    private fun setToggleView(isLoading: Boolean) {
        binding.apply {
            progressBar.isVisible = isLoading
            containerBtn.root.isVisible = !isLoading
            nestedScrollView.isVisible = !isLoading
        }
    }


    private fun setupCreateProduct() {
        binding.containerBtn.btnPrimary.isEnabled = true

        binding.containerBtn.btnPrimary.setOnClickListener {
            Log.e("LIST", listTempVariant.toString())
        }
    }

    private fun setupUI(data: ListTypeAndCategory.Success?) {
        val listUnit = requireActivity().resources.getStringArray(R.array.unit).toList()

        binding.apply {
            data?.apply {
                dropdownChildCategoryProduct.setDropDown(categories.map { it.categoryName })
                dropdownChildTypeProduct.setDropDown(type.map { it.typeName })
            }

            dropdownChildSatuan.apply { setDropDown(listUnit) }
                .setOnItemClickListener { _, _, position, _ ->
                    val unit = listUnit[position]
                    val itemCount = variantAdapter.itemCount
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
                variantAdapter.notifyItemChanged(0)
            }

            if (itemCount >= 1 && unit == resources.getString(R.string.text_pcs)) {
                idVariant = 0
                _listTempVariant.removeAll { it.id > 1 }
                variantAdapter.notifyItemRangeRemoved(1, itemCount - 1)
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
        variantAdapter.notifyItemInserted(idVariant)
    }


    private fun AutoCompleteTextView.setDropDown(listString: List<String>) {
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_text, listString)
        setAdapter(arrayAdapter)
    }


    private fun setupNavigation() {
        binding.incAddImages.apply {
            tvAddPhoto.setOnClickListener {
                findNavController().navigate(R.id.moveToGallery)
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