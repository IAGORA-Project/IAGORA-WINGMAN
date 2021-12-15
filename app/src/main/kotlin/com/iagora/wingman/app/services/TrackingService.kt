package com.iagora.wingman.app.services

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.iagora.wingman.app.R
import com.iagora.wingman.app.ui.main_menu.MainActivity

class TrackingService(): LifecycleService() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var reference: DatabaseReference
    private lateinit var wingmanId: String

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent.let {
            wingmanId = it?.action.toString()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    @SuppressLint("VisibleForTests")
    override fun onCreate() {
        super.onCreate()

        reference = database.reference.child("Geolocation")
        fusedLocationProviderClient = FusedLocationProviderClient(this)
        updateLocationTracking()
        startForegroundService()
    }

    val locationCallBack = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)

            for (location in locationResult.locations){
                 saveLocation(location)
//                 println("MYLODDDD lat: ${location.latitude}, long: ${location.longitude} acc: ${location.accuracy}")
            }
        }
    }


    private fun updateLocationTracking() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            println("benarbenene")

            val request = LocationRequest.create().apply {
                interval = 2000L
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


    private fun startForegroundService() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder = NotificationCompat.Builder(this, "tracking_channel")
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_close)
            .setContentTitle("Ruuning IAGORA Wingman")
            .setContentText("Tracking lokasi aktif")
//            .setContentIntent(getMainActivityPendingInten())

        startForeground(1, notificationBuilder.build())
    }


    @SuppressLint("UnspecifiedImmutableFlag")
    private fun getMainActivityPendingInten() = PendingIntent.getActivity(
        this, 0, Intent(this, MainActivity::class.java), FLAG_UPDATE_CURRENT
    )


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager){
        val channel = NotificationChannel(
            "tracking_channel",
            "Tracking",
            IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }

    // Firebase
    private fun saveLocation(location: Location) {
        println("SAVEEEEEE $location")
        println("wingman_id $wingmanId")
        reference.child(wingmanId).setValue(location)
    }


}