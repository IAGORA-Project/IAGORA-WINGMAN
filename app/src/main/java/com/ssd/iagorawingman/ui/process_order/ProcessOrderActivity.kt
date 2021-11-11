package com.ssd.iagorawingman.ui.process_order

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssd.iagorawingman.databinding.ActivityProcessOrderBinding

class ProcessOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProcessOrderBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProcessOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleHeaderView()
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
}