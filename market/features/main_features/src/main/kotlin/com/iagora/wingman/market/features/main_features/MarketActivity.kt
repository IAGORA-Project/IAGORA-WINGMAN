package com.iagora.wingman.market.features.main_features

import com.iagora.wingman.commons.ui.base.BaseActivity
import com.iagora.wingman.market.features.main_features.databinding.ActivityMarketBinding

class MarketActivity : BaseActivity<ActivityMarketBinding>({ ActivityMarketBinding.inflate(it) }) {
    override fun setView() {
        binding.incHeader.apply {
            tvTitle.text = resources.getString(R.string.text_market)
            ivBackButton.setOnClickListener {
                onBackPressed()
            }
        }
    }
}