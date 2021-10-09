package com.ssd.iagorawingman.ui.process_order

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.ssd.iagorawingman.databinding.ActivityProcessOrderBinding
import com.ssd.iagorawingman.ui.process_order.finished.FinishedFragment
import com.ssd.iagorawingman.ui.process_order.on_process.OnProcessFragment
import com.ssd.iagorawingman.ui.process_order.paid.PaidFragment
import com.ssd.iagorawingman.ui.process_order.sent.SentFragment

class ProcessOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProcessOrderBinding
    private val titleTabLayout = mutableListOf<String>()
    private val listFragment = mutableListOf<Fragment>()
    private var positionTab: Int = 0


    companion object {
        fun newInstance(context: Context, positionTab: Int) {
            val dataIntent = Intent(context, ProcessOrderActivity::class.java)
            dataIntent.putExtra("position-tab", positionTab)
            context.startActivity(dataIntent)
        }
    }

    private fun initBundle() {
        positionTab = intent.getIntExtra("position-tab", 0)
        Log.d("positionTab", positionTab.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProcessOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBundle()
        handleHeaderView()
        handleTabViewPager()
    }

    private fun handleHeaderView(){
        binding.incHeader.ivBackButton.setOnClickListener {
            onBackPressed()
        }
        binding.incHeader.tvTitle.text = "Orderan Saya"
        binding.incHeader.containerToolbar.elevation = 0f
    }

    private fun handleTabViewPager() {
        listFragment.add(OnProcessFragment())
        listFragment.add(PaidFragment())
        listFragment.add(SentFragment())
        listFragment.add(FinishedFragment())

        titleTabLayout.add("Dalam Proses")
        titleTabLayout.add("Sudah Dibayar")
        titleTabLayout.add("Dikirim")
        titleTabLayout.add("Selesai")

        val sectionsPagerAdapter = ProcessOrderTabLayoutAdapter(this, listFragment)
        binding.vpTabs.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.vpTabs) { tab, position ->
            tab.text = titleTabLayout[position]
        }.attach()

        binding.vpTabs.setCurrentItem(positionTab, true)
    }



}