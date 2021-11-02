package com.ssd.iagorawingman.utils

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import com.ssd.iagorawingman.services.FirebaseNotificationService

class TriggerWhatsApp {
    companion object {
        fun appInstalledOrNot(uri: String, packageManager: PackageManager): Boolean {
            val pm = packageManager
            return try {
                pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
                true
            } catch (e: PackageManager.NameNotFoundException) {
                false
            }
        }

        fun openWhatsApp(number: String, message: String, packageManager: PackageManager, activity: FirebaseNotificationService) {

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=$number&text=$message"))
                activity.startActivity(intent)

        }
    }
}