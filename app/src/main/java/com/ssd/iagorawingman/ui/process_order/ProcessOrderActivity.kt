package com.ssd.iagorawingman.ui.process_order


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.ActivityProcessOrderBinding


class ProcessOrderActivity : AppCompatActivity() {
    private var _binding: ActivityProcessOrderBinding? = null
    private val binding get() = _binding as ActivityProcessOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProcessOrderBinding.inflate(layoutInflater)
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
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}