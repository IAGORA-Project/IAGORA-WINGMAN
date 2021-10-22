package com.ssd.iagorawingman.ui.chat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.ssd.iagorawingman.databinding.ActivityChatBinding
import com.ssd.iagorawingman.ui.chat.finished_chat.FinishedChatFragment
import com.ssd.iagorawingman.ui.chat.ongoing.OnGoingFragment
import com.ssd.iagorawingman.ui.process_order.ProcessOrderTabLayoutAdapter
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val titleTabLayout = mutableListOf<String>()
    private val listFragment = mutableListOf<Fragment>()

    companion object {
        fun newInstance(context: Context) {
            val dataIntent = Intent(context, ChatActivity::class.java)
            context.startActivity(dataIntent)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleHeaderView()
        handleTabViewPager()
    }

    private fun handleHeaderView(){
        binding.incHeader.ivBackButton.setOnClickListener {
            onBackPressed()
        }
        binding.incHeader.tvTitle.text = "Chat"
        binding.incHeader.containerToolbar.elevation = 0f
    }

    private fun handleTabViewPager() {
        listFragment.add(OnGoingFragment())
        listFragment.add(FinishedChatFragment())

        titleTabLayout.add("Sedang Berlangsung")
        titleTabLayout.add("Selesai")

        val sectionsPagerAdapter = ProcessOrderTabLayoutAdapter(this, listFragment)
        binding.vpTabs.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.vpTabs) { tab, position ->
            tab.text = titleTabLayout[position]
        }.attach()
    }

}