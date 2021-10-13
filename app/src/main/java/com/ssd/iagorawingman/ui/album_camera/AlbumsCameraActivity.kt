package com.ssd.iagorawingman.ui.album_camera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.ssd.iagorawingman.databinding.ActivityAlbumCameraBinding
import com.ssd.iagorawingman.ui.album_camera.albums.AlbumsFragment
import com.ssd.iagorawingman.ui.album_camera.camera.CameraFragment
import com.ssd.iagorawingman.ui.process_order.ProcessOrderTabLayoutAdapter

class AlbumsCameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumCameraBinding
    private val titleTabLayout = mutableListOf<String>()
    private val listFragment = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleView()
        handleTabViewPager()
    }

    private fun handleView(){
        binding.incHeader.tvNext.text = "Lanjut"
        binding.incHeader.tvNext.setOnClickListener {
            onBackPressed()
        }

        binding.incHeader.ivBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun handleTabViewPager() {
        //disable swipe view pager
        binding.vpTabs.isUserInputEnabled = false

        listFragment.add(AlbumsFragment())
        listFragment.add(CameraFragment())

        titleTabLayout.add("Album")
        titleTabLayout.add("Kamera")

        val sectionsPagerAdapter = ProcessOrderTabLayoutAdapter(this, listFragment)
        binding.vpTabs.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.vpTabs) { tab, position ->
            tab.text = titleTabLayout[position]
        }.attach()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        println("SOJSIOSJS ${permissions}")
    }
}