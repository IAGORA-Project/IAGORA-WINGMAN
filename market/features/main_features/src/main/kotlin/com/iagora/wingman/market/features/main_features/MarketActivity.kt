package com.iagora.wingman.market.features.main_features

import com.iagora.wingman.commons.ui.base.BaseActivity
import com.iagora.wingman.market.features.main_features.databinding.ActivityMarketBinding

class MarketActivity : BaseActivity<ActivityMarketBinding>({ ActivityMarketBinding.inflate(it) }) {
    override fun setupToolbar() {
        super.setupToolbar()
        setSupportActionBar(binding.incHeader.toolbar)
        supportActionBar?.title = resources.getString(R.string.text_market)
    }
}