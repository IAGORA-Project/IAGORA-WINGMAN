package com.ssd.iagorawingman.ui.main_menu

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.database.*
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.local.model.MyLocation
import com.ssd.iagorawingman.databinding.ActivityMainBinding
import com.ssd.iagorawingman.services.TrackingService

import com.ssd.iagorawingman.data.source.remote.response.ResGetWingmanInfo
import com.ssd.iagorawingman.services.BroadcastService
import com.ssd.iagorawingman.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity()   {

    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: LocationManager
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var referense: DatabaseReference
    private val mainViewModel: MainViewModel by viewModel()
    private var wingman_id: ResGetWingmanInfo? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

//        val getWingmanInfo = sharedWingmanInfoRepository.getWingmanInfo(BuildConfig.KEY_SHARED_PREFERENCE_WINGMAN_INFO)


        manager = getSystemService(LOCATION_SERVICE) as LocationManager

//        referense = database.reference.child("Geolocation").child(wingman_id?.success?.idKol)

//        wingman_id = mainViewModel.getWingmanInfo!!




        getWingmanInfo()
        getSharedWingmanInfo()
        getLocationUpdate()
//        readChanges() // read realtime-database firebase
    }


    private fun getLocationUpdate() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                startService()
        }else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101)
        }
    }


    private fun startService() {
        Intent(this, TrackingService::class.java).also {
            it.action = wingman_id?.success?.idKol
            this.startService(it)
        }
    }

    private fun getSharedWingmanInfo(){
        mainViewModel.vmGetSharedWingmanInfo().observe(this, {
            println("KDJHFJKFJKFHJKFHF ${it}")
        })
    }


    private fun readChanges() {
        referense.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                println("KJDJKDHKJDHKJDHDJD $snapshot")
               if(snapshot.exists()){
                   try {
                       val location: MyLocation? = snapshot.getValue(MyLocation::class.java)
                       if(location != null){
                            // bisa kasi marker
                           println("snapshotsnapshot $snapshot")
                           println("locationlocation11 $location")
                       }
                   }catch (e: Exception){
                       println("errorlocation $e")
                       Toast.makeText(this@MainActivity, "Error readChanges", Toast.LENGTH_SHORT).show()
                   }
               }
            }

            override fun onCancelled(error: DatabaseError) {
                println("cancelleddd $error")
            }
        })
    }

    private fun getWingmanInfo() {
        mainViewModel.vmGetWingmanInfo().observe(this, {
            it.getContentIfNotHandled().let { res ->
                when(res?.status){
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        println("jadasdhajsdhajshd ${res.data}")
                        wingman_id = res.data
                    }
                    Status.ERROR -> {

                    }
                }
            }
        })
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 101){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLocationUpdate()
                println("permissionngranted")
            }else{
                Toast.makeText(this, "Permission Required", Toast.LENGTH_SHORT).show()
            }
        }
    }

}