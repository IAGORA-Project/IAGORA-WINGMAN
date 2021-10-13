package com.ssd.iagorawingman.utils

import android.Manifest


object Constants {

    const val TAG = "cameraX"
    const val FILE_NAME_FORMAT = "yyyy-MM-dd-HH-mm"
    const val REQUEST_CODE_PERMISSIONS = 123
    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
}