package com.ssd.iagorawingman.services

import android.content.Context
import android.content.Intent
import android.location.Location
import androidx.lifecycle.LifecycleService
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseSaveDataLocation: LifecycleService() {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var referense: DatabaseReference
    private var locationNow: Location? = null


    companion object {
        fun newInstance(context: Context, location: Location) {
            val dataIntent = Intent(context, FirebaseSaveDataLocation::class.java)
            dataIntent.putExtra("my-loc", location)
            context.startService(dataIntent)
        }
    }


    override fun onCreate() {
        super.onCreate()
        referense = database.reference.child("Geolocation")
    }

    private fun saveLocation(location: Location) {
        println("SAVEEEEEE $location")
        referense.child("Wingman-01").setValue(location)
    }
}

