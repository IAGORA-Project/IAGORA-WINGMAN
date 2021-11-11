package com.ssd.iagorawingman.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

fun getImageResized(context: Context, selectedImage: Uri): Bitmap {
    var bm: Bitmap? = null
    val sampleSizes = intArrayOf(10, 8, 5, 3, 2, 1)
    var i = 0
    val minWidthQuality = 500
    do {
        bm = decodeBitmap(context, selectedImage, sampleSizes[i])
        i++
    } while (bm!!.width < minWidthQuality && i < sampleSizes.size)
    return bm
}

fun decodeBitmap(context: Context, theUri: Uri, sampleSize: Int? = null): Bitmap? {
    var inputStream = context.contentResolver.openInputStream(theUri)
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeStream(inputStream, null, options)
    if(null != sampleSize) options.inSampleSize = sampleSize
    inputStream!!.close()
    inputStream = context.contentResolver.openInputStream(theUri)
    options.inJustDecodeBounds = false
    return BitmapFactory.decodeStream(inputStream, null, options)
}

fun Context.persistImage(bitmap: Bitmap, name: String): File {
    val filesDir: File = this.filesDir
    val imageFile = File(filesDir, name)
    var os: OutputStream? = null
    try {
        os = FileOutputStream(imageFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os)
        os.flush()
        os.close()
    } catch (e: Exception) {
    }
    return imageFile
}