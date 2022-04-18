package com.iagora.wingman.market.presentation.list_market

import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.extensions.collectWhenStarted
import com.iagora.wingman.market.presentation.R
import com.iagora.wingman.market.presentation.databinding.FragmentListMarketBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListMarketFragment : BaseFragment<FragmentListMarketBinding>(
    R.layout.fragment_list_market,
    { FragmentListMarketBinding.bind(it) }) {

    private val viewModel: ListMarketViewModel by viewModel()
    private lateinit var adapter: ListMarketAdapter

    override fun setTitleToolbar(supportActionBar: ActionBar?) {
        super.setTitleToolbar(supportActionBar)
        supportActionBar?.title = resources.getString(R.string.text_market)
    }

    override fun setView() {
        viewModel.listMarket.collectWhenStarted(viewLifecycleOwner) { state ->
            adapter.submitList(state.data?.success)
            binding.progressBar.isVisible = state.isLoading
        }

        binding.btnAddMarket.setOnClickListener {
            findNavController().navigate(R.id.action_listMarketFragment_to_addMarketFragment)
        }
    }

    override fun setAdapter() {
        adapter = ListMarketAdapter()
        binding.rvListMarket.adapter = adapter

        adapter.setOnItemClickListener {
            navigate(it)
        }
    }

    private fun navigate(idMarket: String) {
        findNavController().navigate(ListMarketFragmentDirections.moveToListProduct(idMarket))
    }

    override fun onBackPressed() = requireActivity().finish()
}

