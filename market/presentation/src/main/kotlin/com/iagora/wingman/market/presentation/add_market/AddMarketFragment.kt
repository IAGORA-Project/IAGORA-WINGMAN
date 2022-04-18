package com.iagora.wingman.market.presentation.add_market

import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.market.presentation.R
import com.iagora.wingman.market.presentation.databinding.FragmentAddMarketBinding

class AddMarketFragment : BaseFragment<FragmentAddMarketBinding>(R.layout.fragment_add_market, {FragmentAddMarketBinding.bind(it)}) {
    override fun setView() {

    }

    override fun setTitleToolbar(supportActionBar: ActionBar?) {
        super.setTitleToolbar(supportActionBar)
        supportActionBar?.title = "Tambah Pasar"
    }
}