package com.iagora.wingman.process_order.features.main_features


import com.iagora.wingman.core.presentation.base.BaseActivity
import com.iagora.wingman.process_order.features.main_features.databinding.ActivityProcessOrderBinding


class ProcessOrderActivity :
    BaseActivity<ActivityProcessOrderBinding>({ ActivityProcessOrderBinding.inflate(it) }) {

    override fun setView() {
        super.setView()
        setupToolbar(binding.incHeader.toolbar)
    }

    companion object {
        const val POST_TAB = "positionTab"
    }

}