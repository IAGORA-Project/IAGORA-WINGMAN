package com.ssd.iagorawingman.ui.receive_order

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssd.iagorawingman.databinding.ActivityReceiveOrderBinding

class ReceiveOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReceiveOrderBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiveOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}