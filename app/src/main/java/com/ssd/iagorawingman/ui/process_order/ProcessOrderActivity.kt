package com.ssd.iagorawingman.ui.process_order


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.ActivityProcessOrderBinding
import com.ssd.iagorawingman.ui.main_menu.MainActivity


class ProcessOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProcessOrderBinding
    private var backStep = 2


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
            incHeader.tvTitle.text = resources.getString(R.string.title_toolbar_process_order)
            incHeader.containerToolbar.elevation = 0f
        }
    }

    companion object {
        const val POST_TAB = "positionTab"
        const val IS_FROM_RECEIVER = "fromReceiver"
    }

}