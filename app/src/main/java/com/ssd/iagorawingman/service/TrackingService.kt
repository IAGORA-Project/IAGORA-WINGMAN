package com.ssd.iagorawingman.service

import android.Manifest
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LifecycleService
import com.google.android.gms.location.*

class TrackingService: LifecycleService() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationCallback: LocationCallback

    override fun onCreate() {
        super.onCreate()

        fusedLocationProviderClient = FusedLocationProviderClient(this)
        updateLocationTracking()


        println("JAALAANAAHALANAAN")
    }

    val locationCallBack = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)

            println("DJDJDHDKJDHD $locationResult")

            for (location in locationResult.locations){
                println("MYLODDDD lat: ${location.latitude}, long: ${location.longitude} acc: ${location.accuracy}")
            }

        }
    }

    private fun updateLocationTracking() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            println("benarbenene")

            val request = LocationRequest.create().apply {
                interval = 3000L
                fastestInterval = 2000L
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }

            fusedLocationProviderClient.requestLocationUpdates(
                request,
                locationCallBack,
                Looper.getMainLooper()
            )
        }else{
            println("ELSKSJSKJS")
        }
    }





}