package com.ssd.iagorawingman.ui.process_order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.ActivityProcessOrderBinding

class ProcessOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProcessOrderBinding


    companion object {
        fun newInstance(context: Context, positionTab: Int) {
            val dataIntent = Intent(context, ProcessOrderActivity::class.java)
            dataIntent.putExtra("position-tab", positionTab)
            context.startActivity(dataIntent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProcessOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        handleHeaderView()
        handleTabViewPager()
    }

    private fun handleHeaderView() {
        binding.apply {
            incHeader.ivBackButton.setOnClickListener {
                onBackPressed()
            }
            incHeader.tvTitle.text = StringBuilder("Orderan Saya")
            incHeader.containerToolbar.elevation = 0f
        }
    }

    private fun handleTabViewPager() {
        val tabTitle = this.resources.getStringArray(R.array.tab_title_order)
        val sectionsPagerAdapter = ProcessOrderTabLayoutAdapter(this)

        binding.apply {

            vpTabs.apply {
                adapter = sectionsPagerAdapter
                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            }

            TabLayoutMediator(tabs, vpTabs) { tab, position ->
                tab.text = tabTitle[position]
            }.attach()

        }
    }


}