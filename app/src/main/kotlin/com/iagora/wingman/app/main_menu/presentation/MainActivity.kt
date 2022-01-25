package com.iagora.wingman.app.main_menu.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import com.iagora.wingman.app.R
import com.iagora.wingman.app.databinding.ActivityMainBinding
import com.iagora.wingman.app.main_menu.domain.model.MyLocation
import com.iagora.wingman.app.main_menu.presentation.home.HomeViewModel
import com.iagora.wingman.core.presentation.base.BaseActivity
import com.iagora.wingman.core.presentation.extensions.collectWhenStarted
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    private lateinit var manager: LocationManager


    private val homeViewModel: HomeViewModel by viewModel()


    override fun setView() {

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)


        manager = getSystemService(LOCATION_SERVICE) as LocationManager


    }






    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 101) {
            if (grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Required", Toast.LENGTH_SHORT).show()
            }
        }
    }

}