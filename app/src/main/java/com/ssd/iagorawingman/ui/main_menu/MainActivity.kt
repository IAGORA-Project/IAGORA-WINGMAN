package com.ssd.iagorawingman.ui.main_menu

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.firebase.database.*
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.local.model.MyLocation
import com.ssd.iagorawingman.databinding.ActivityMainBinding
import com.ssd.iagorawingman.service.TrackingService

class MainActivity : AppCompatActivity(), LocationListener {

    private lateinit var binding: ActivityMainBinding
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var referense: DatabaseReference
    private lateinit var manager: LocationManager
    var MIN_TIME: Long = 1000L // 1 sec
    var MIN_DISTANCE: Float = 1f // 1 meter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        manager = getSystemService(LOCATION_SERVICE) as LocationManager

        referense = database.reference.child("Geolocation")

        getLocationUpdate()
        readChanges() // read realtime-database firebase

    }


    override fun onLocationChanged(location: Location) {
        println("OKEEEEE123")
        println("livelocation ${location.latitude}")
        if(location != null){
            saveLocation(location)
            println("livelocationada")
        }else{
            println("livelocation-tidakada")
            Toast.makeText(this, "Tidak dapat lokasi", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onProviderEnabled(provider: String) {
        super.onProviderEnabled(provider)

        println("PROVIDERENABLE")
        getLocationUpdate()
    }

    override fun onProviderDisabled(provider: String) {
        super.onProviderDisabled(provider)

        println("PROVIDERDISABLE")
    }

    private fun saveLocation(location: Location) {
        println("SAVEEEEEE $location")
        referense.child("Wingman-01").setValue(location)
    }

    private fun getLocationUpdate() {
        println("jksdfjdskfsdkjf $manager")
        if(manager != null){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    println("GPSNEWTWorK ${manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)}")

                    manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, this)
                } else if(manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                    println("NETWORKPROVIDER")
                    manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this)
                } else{
                    Toast.makeText(this, "Error No Provider", Toast.LENGTH_SHORT).show()
                }
            }else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101)
            }
        }
    }

    private fun readChanges() {
        referense.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
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