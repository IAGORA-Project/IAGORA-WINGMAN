package com.iagora.wingman.core.presentation.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


object CameraPermission {
    fun checkPermissions(ctx: Context): Boolean {
        return REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                ctx,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun requestPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity, REQUIRED_PERMISSIONS,
            REQUEST_CODE_PERMISSIONS
        )
    }


    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    private const val REQUEST_CODE_PERMISSIONS = 123
    const val DELAY_SETUP_CAMERA = 300L
    const val FILE_NAME_FORMAT = "yyyy-MM-dd-HH-mm"
}