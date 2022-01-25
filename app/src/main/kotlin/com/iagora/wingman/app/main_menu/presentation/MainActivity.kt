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
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var referense: DatabaseReference

    private val homeViewModel: HomeViewModel by viewModel()


    override fun setView() {

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)


        manager = getSystemService(LOCATION_SERVICE) as LocationManager
        referense = database.reference.child("Geolocation")



        handleStatusBar()
//        readChanges() // read realtime-database firebase
    }


    private fun handleStatusBar() {
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.statusBarColor = this.getColor(R.color.redPrimary)
    }


    private fun getLocationUpdate(wingman_id: String) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {

        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101)
        }
    }


    override fun onResume() {
        super.onResume()
        getWingmanInfo()
    }


    private fun readChanges() {
        referense.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    try {
                        val location: MyLocation? = snapshot.getValue(MyLocation::class.java)
                        if (location != null) {
                            // bisa kasi marker
                            println("snapshotsnapshot $snapshot")
                            println("locationlocation11 $location")
                        }
                    } catch (e: Exception) {
                        println("errorlocation $e")
                        Toast.makeText(this@MainActivity, "Error readChanges", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("cancelleddd $error")
            }
        })
    }


    private fun getWingmanInfo() {
        homeViewModel.dataWingman.collectWhenStarted(this) { state ->
            val id = state.wingmanInfo?.success?.idKol ?: ""
            getLocationUpdate(id)
        }
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