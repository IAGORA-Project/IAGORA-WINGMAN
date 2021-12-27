package com.iagora.wingman.market.features.list_product_market

import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.iagora.wingman.commons.ui.base.BaseFragment
import com.iagora.wingman.commons.ui.extensions.collectWhenCreated
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.helper.set
import com.iagora.wingman.market.features.list_product_market.databinding.FragmentListProductMarketBinding
import com.iagora.wingman.market.helper.model.response.ListProduct
import com.iagora.wingman.market.viewmodels.ListProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListProductFragment :
    BaseFragment<FragmentListProductMarketBinding>(R.layout.fragment_list_product_market,
        { FragmentListProductMarketBinding.bind(it) }) {

    private val adapter: ListProductAdapter by lazy { ListProductAdapter() }
    private val viewModel: ListProductViewModel by viewModel()
    private val args by navArgs<ListProductFragmentArgs>()

    override fun setTitleToolbar(supportActionBar: ActionBar?) {
        super.setTitleToolbar(supportActionBar)
        supportActionBar?.title = resources.getString(R.string.text_product_market)
    }


    override fun setAdapter() {
        super.setAdapter()
        val idMarket = args.idMarket

        binding.rvListProduct.adapter = adapter
        viewModel.setIdMarket(idMarket)
        moveToAddProduct(idMarket)
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

    private fun subscribeToViewModel() {
        viewModel.vmGetListProductMarket.collectWhenCreated(this) { res ->
            handleUI(res)
        }
    }

    private fun handleUI(res: Resource<ListProduct>) {
        res.apply {
            set(
                error = {
                    setVisibilityLayout(false, error = true)
                },
                success = {
                    viewModel.setData(data)
                    handleUISuccess(data?.success)
                },
                loading = {
                    setVisibilityLayout(false)
                }
            )
        }
    }

    private fun handleUISuccess(data: List<ListProduct.Success>?) {
        adapter.submitList(data)
        setVisibilityLayout(true)
    }

    private fun moveToAddProduct(idMarket: String) {
        binding.btnAddProduct.setOnClickListener {
            findNavController().navigate(ListProductFragmentDirections.moveToAddProduct(idMarket))
        }
    }

    private fun setVisibilityLayout(isVisible: Boolean, error: Boolean = false) {
        binding.apply {
            if (error) {
                btnAddProduct.isVisible = isVisible
                rvListProduct.isVisible = isVisible
                progressBar.isVisible = isVisible
            } else {
                btnAddProduct.isVisible = isVisible
                rvListProduct.isVisible = isVisible
                progressBar.isVisible = !isVisible
            }
        }
    }


}