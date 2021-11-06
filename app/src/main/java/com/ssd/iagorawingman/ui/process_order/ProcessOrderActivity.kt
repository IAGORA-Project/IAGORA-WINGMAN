package com.ssd.iagorawingman.ui.process_order

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.ssd.iagorawingman.databinding.ActivityProcessOrderBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

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