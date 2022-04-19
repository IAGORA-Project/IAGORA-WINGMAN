package com.iagora.wingman.market.presentation

import com.iagora.wingman.core.presentation.base.BaseActivity
import com.iagora.wingman.market.presentation.databinding.ActivityMarketBinding

class MarketActivity : BaseActivity<ActivityMarketBinding>({ ActivityMarketBinding.inflate(it) }) {

    override fun setView() {
        super.setView()
        setupToolbar(binding.incHeader.toolbar)
    }

}