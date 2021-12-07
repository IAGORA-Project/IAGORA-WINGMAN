package com.iagora.wingman.process_order.features.main_features


import com.iagora.wingman.commons.ui.base.BaseActivity
import com.iagora.wingman.process_order.features.main_features.databinding.ActivityProcessOrderBinding


class ProcessOrderActivity :
    BaseActivity<ActivityProcessOrderBinding>({ ActivityProcessOrderBinding.inflate(it) }) {

    override fun setView() {
        handleHeaderView()
    }

    private fun handleHeaderView() {
        binding.apply {
            incHeader.ivBackButton.setOnClickListener {
                onBackPressed()
            }
            incHeader.tvTitle.text = resources.getString(R.string.title_toolbar_process_order)
            incHeader.containerToolbar.elevation = 0f
        }
    }

    companion object {
        const val POST_TAB = "positionTab"
    }

}