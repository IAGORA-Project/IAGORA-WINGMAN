package com.iagora.wingman.market.presentation.list_product

import androidx.appcompat.app.ActionBar
import androidx.core.view.TintableBackgroundView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.extensions.collectWhenStarted
import com.iagora.wingman.market.presentation.R
import com.iagora.wingman.market.presentation.databinding.FragmentListProductMarketBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber


class ListProductFragment :
    BaseFragment<FragmentListProductMarketBinding>(
        R.layout.fragment_list_product_market,
        { FragmentListProductMarketBinding.bind(it) }) {

    private lateinit var actionBar: ActionBar
    private lateinit var adapter: ListProductAdapter
    private val viewModel: ListProductViewModel by viewModel { parametersOf(defaultMarket) }

    override fun setTitleToolbar(supportActionBar: ActionBar?) {
        super.setTitleToolbar(supportActionBar)
        actionBar = supportActionBar!!
        actionBar.title = "Pasar ... -Product"
    }


    override fun setView() {
        viewModel.listProduct.collectWhenStarted(viewLifecycleOwner) { state ->
            actionBar.title = "Pasar ${state.data?.name} - Product"

            Timber.tag("yy").d("${state.data?.product}")

            adapter.submitList(state.data?.product)
            setToggleView(state.isLoading)
        }
        setToggleView(false)
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

    override fun onBackPressed() = requireActivity().finish()

    companion object{
        const val defaultMarket = "62308c41b9085633b0e01179"
    }
}