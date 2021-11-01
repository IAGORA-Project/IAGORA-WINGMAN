package com.ssd.iagorawingman.ui.process_order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentProcessOrderBinding

class ProcessOrderFragment : Fragment(R.layout.fragment_process_order) {

    private lateinit var binding: FragmentProcessOrderBinding


    companion object {
        fun newInstance(context: Context, positionTab: Int) {
            val dataIntent = Intent(context, ProcessOrderFragment::class.java)
            dataIntent.putExtra("position-tab", positionTab)
            context.startActivity(dataIntent)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProcessOrderBinding.bind(view)
        handleHeaderView()
        handleTabViewPager()
    }


    private fun handleHeaderView() {
        binding.apply {
            incHeader.ivBackButton.setOnClickListener {
                findNavController().popBackStack()
            }
            incHeader.tvTitle.text = StringBuilder("Orderan Saya")
            incHeader.containerToolbar.elevation = 0f
        }
    }

    private fun handleTabViewPager() {
        val tabTitle = this.resources.getStringArray(R.array.tab_title_order)
        val sectionsPagerAdapter =
            ProcessOrderTabLayoutAdapter(requireActivity() as AppCompatActivity)

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