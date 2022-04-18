package com.iagora.wingman.core.presentation.util

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat

object Permission {
    fun forceFullOpenPermission(activity: Activity) {
        try {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", activity.packageName, null)
            intent.data = uri
            activity.startActivity(intent)
        }catch (e: Exception){ }
    }

    fun hasPermission(activity: Activity, permission: String) : Boolean{
        return ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermissions(activity: Activity, permission: Array<String>, requestId: Int) {
        ActivityCompat.requestPermissions(activity, permission, requestId)
    }

    fun isPermissionGranted(
        grantPermissions: Array<out String>, grantResults: IntArray,
        permission: String
    ): Boolean {
        for (i in grantPermissions.indices) {
            if (permission == grantPermissions[i]) {
                return grantResults[i] == PackageManager.PERMISSION_GRANTED
            }
        }
        return false
    }

}