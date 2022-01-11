package com.iagora.wingman.commons.views.helper

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings

object Permission {

    //Untuk membuka halaman info aplikasi
    fun forceFullOpenPermission(activity: Activity) {
        try {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", activity.packageName, null)
            intent.data = uri
            activity.startActivity(intent)
        }catch (e: Exception){ }
    }

}