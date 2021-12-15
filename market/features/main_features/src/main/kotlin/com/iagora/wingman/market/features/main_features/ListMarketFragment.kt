package com.iagora.wingman.market.features.main_features

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.iagora.wingman.commons.ui.base.BaseFragment
import com.iagora.wingman.commons.ui.extensions.collectWhenCreated
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.helper.set
import com.iagora.wingman.market.features.main_features.databinding.FragmentListMarketBinding
import com.iagora.wingman.market.helper.model.response.ListMarket
import com.iagora.wingman.market.viewmodels.ListMarketViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListMarketFragment : BaseFragment<FragmentListMarketBinding>(R.layout.fragment_list_market,
    { FragmentListMarketBinding.bind(it) }) {

    private val viewModel: ListMarketViewModel by viewModel()
    private lateinit var adapter: ListMarketAdapter

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
        viewModel.getListMarket.collectWhenCreated(this) { res ->
            handleUI(res)
        }
    }

    private fun handleUI(res: Resource<ListMarket>) {
        res.apply {
            set(
                error = {
                    setVisibilityLayout(false, error = true)
                },
                success = {
                    viewModel.setData(data)
                    handleUISuccess(data?.success)
                },
                loading = { setVisibilityLayout(false) }
            )
        }
    }

    private fun handleUISuccess(data: List<ListMarket.Success>?) {
        adapter.apply {
            submitList(data)
            setOnItemClickListener {idMarket ->

            }
        }
        setVisibilityLayout(true)
    }


    override fun setAdapter() {
        super.setAdapter()
        adapter = ListMarketAdapter()
        binding.rvListPasar.adapter = adapter
    }

    private fun setVisibilityLayout(isVisible: Boolean, error: Boolean = false) {
        binding.apply {
            if (error) {
                rvListPasar.isVisible = isVisible
                progressBar.isVisible = isVisible
            } else {
                rvListPasar.isVisible = isVisible
                progressBar.isVisible = !isVisible
            }
        }
    }
}

