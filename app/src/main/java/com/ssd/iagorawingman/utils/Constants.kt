package com.ssd.iagorawingman.utils

import android.Manifest


object Constants {

    const val TAG = "cameraX"
    const val FILE_NAME_FORMAT = "yyyy-MM-dd-HH-mm"
    const val REQUEST_CODE_PERMISSIONS = 123
    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)


    const val ACTION_START_OR_RESUME_SERVICE = "ACTION_START_OR_RESUME_SERVICE"
    const val ACTION_PAUSE_SERVICE = "ACTION_PAUSE_SERVICE"
    const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"

    const val WAITING_CONFIRMATION = "WAITING_CONFIRMATION"
    const val WAITING_CONFIRMED = "CONFIRMATION"
    const val WAITING_PAYMENT = "WAITING_PAYMENT"

    const val ACCEPT = "accept"
    const val DECLINE = "decline"
}