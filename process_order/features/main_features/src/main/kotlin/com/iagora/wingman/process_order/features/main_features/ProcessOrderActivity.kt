package com.iagora.wingman.process_order.features.main_features


import com.iagora.wingman.commons.ui.base.BaseActivity
import com.iagora.wingman.process_order.features.main_features.databinding.ActivityProcessOrderBinding


class ProcessOrderActivity :
    BaseActivity<ActivityProcessOrderBinding>({ ActivityProcessOrderBinding.inflate(it) }) {

    override fun setupToolbar() {
        super.setupToolbar()
        setSupportActionBar(binding.incHeader.toolbar)
        supportActionBar?.title = getString(R.string.title_toolbar_process_order)
    }

    companion object {
        const val POST_TAB = "positionTab"
    }

}