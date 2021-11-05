package com.ssd.iagorawingman.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.WindowManager

object Other {
    fun dpToPx(dp: Int): Int {
        return ((dp * Resources.getSystem().displayMetrics.density).toInt());
    }

    fun pxToDp(px: Int, context: Context): Int {
        return ((px / Resources.getSystem().displayMetrics.density).toInt());
    }

    fun clearKeyboardAndFocus(activity: Activity){
        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}