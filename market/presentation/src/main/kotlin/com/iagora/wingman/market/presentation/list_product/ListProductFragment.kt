package com.iagora.wingman.market.presentation.list_product

import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.iagora.wingman.commons.ui.base.BaseFragment
import com.iagora.wingman.commons.ui.extensions.collectWhenStarted
import com.iagora.wingman.market.features.list_product_market.ListProductAdapter
import com.iagora.wingman.market.presentation.R
import com.iagora.wingman.market.presentation.databinding.FragmentListProductMarketBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ListProductFragment :
    BaseFragment<FragmentListProductMarketBinding>(
        R.layout.fragment_list_product_market,
        { FragmentListProductMarketBinding.bind(it) }) {

    private lateinit var adapter: ListProductAdapter
    private val navArgs by navArgs<ListProductFragmentArgs>()
    private val viewModel: ListProductViewModel by viewModel { parametersOf(navArgs.idMarket) }

    override fun setTitleToolbar(supportActionBar: ActionBar?) {
        super.setTitleToolbar(supportActionBar)
        supportActionBar?.title = resources.getString(R.string.text_product_market)
    }


    override fun setView() {
        viewModel.listProduct.collectWhenStarted(viewLifecycleOwner) { state ->
            adapter.submitList(state.data?.success)
            setToggleView(state.isLoading)
        }
        setupNavigation()
    }

    private fun setToggleView(isLoading: Boolean) {
        binding.apply {
            progressBar.isVisible = isLoading
            rvListProduct.isVisible = !isLoading
            btnAddProduct.isVisible = !isLoading
        }
    }

    private fun setupNavigation() {
        binding.btnAddProduct.setOnClickListener {
            findNavController().navigate(R.id.moveToAddProduct)
        }
    }

    override fun setAdapter() {
        adapter = ListProductAdapter()
        binding.rvListProduct.adapter = adapter
    }
}