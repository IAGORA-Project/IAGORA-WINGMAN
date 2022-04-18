package com.iagora.wingman.core.presentation.util

import android.content.Context
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.core.presentation.R


fun Snackbar.customPrimaryColor(ctx: Context) {
    view.setBackgroundColor(ctx.getColor(R.color.redPrimary))
    setTextColor(ctx.getColor(R.color.white))
}

fun setMessage(message: String, content: CoordinatorLayout) {
    Snackbar.make(
        content,
        message,
        Snackbar.LENGTH_SHORT
    ).apply { customPrimaryColor(context) }.show()
}

