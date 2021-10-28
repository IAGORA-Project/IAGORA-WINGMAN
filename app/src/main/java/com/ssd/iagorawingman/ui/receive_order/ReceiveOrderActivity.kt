package com.ssd.iagorawingman.ui.receive_order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ssd.iagorawingman.databinding.ActivityReceiveOrderBinding

class ReceiveOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReceiveOrderBinding
    private var dataNotif: String = ""

    private fun initBundle() {
        dataNotif = intent.getStringExtra("data-notif").toString()
        Log.d("dataNotifdataNotif", dataNotif)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiveOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBundle()
    }
}