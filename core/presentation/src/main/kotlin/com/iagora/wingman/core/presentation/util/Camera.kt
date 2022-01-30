package com.iagora.wingman.core.presentation.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.FileProvider
import com.iagora.wingman.core.util.Constants
import java.io.File


object Camera {
     fun getTakenPhoto(fileName: String, activity: Activity): File {
        val storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDir)
    }


    fun openCamera(
        ctx: Context,
        photoFile: File,
    ): Intent {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val fileProvider =
            FileProvider.getUriForFile(ctx, Constants.FILE_PROVIDER, photoFile)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

        return intent
    }
}