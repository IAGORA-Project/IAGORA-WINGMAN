package com.iagora.wingman.app.chat

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.iagora.wingman.app.chat.finished_chat.FinishedChatFragment
import com.iagora.wingman.app.chat.ongoing.OnGoingFragment
import com.iagora.wingman.app.databinding.ActivityChatBinding
import com.iagora.wingman.core.presentation.base.BaseActivity

class ChatActivity : BaseActivity<ActivityChatBinding>({ ActivityChatBinding.inflate(it) }) {

    private val titleTabLayout = mutableListOf<String>()
    private val listFragment = mutableListOf<Fragment>()

    companion object {
        fun newInstance(context: Context) {
            val dataIntent = Intent(context, ChatActivity::class.java)
            context.startActivity(dataIntent)
        }
    }


    override fun setView() {
        handleHeaderView()
        handleTabViewPager()
    }

    private fun handleHeaderView() {


//        binding.incHeader.ivBackButton.setOnClickListener {
//            onBackPressed()
//        }
//        binding.incHeader.tvTitle.text = StringBuilder("Chat")
//        binding.incHeader.containerToolbar.elevation = 0f
    }

    private fun handleTabViewPager() {
        listFragment.add(OnGoingFragment())
        listFragment.add(FinishedChatFragment())

        titleTabLayout.add("Sedang Berlangsung")
        titleTabLayout.add("Selesai")

//        val sectionsPagerAdapter = ProcessOrderTabLayoutAdapter(this, listFragment)
//        binding.vpTabs.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.vpTabs) { tab, position ->
            tab.text = titleTabLayout[position]
        }.attach()
    }

}