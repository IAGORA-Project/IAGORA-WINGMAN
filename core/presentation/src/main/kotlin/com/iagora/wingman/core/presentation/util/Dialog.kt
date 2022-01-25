package com.iagora.wingman.core.presentation.util

import android.content.Context
import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.core.presentation.R


fun Snackbar.customPrimaryColor(ctx: Context) {
    view.setBackgroundColor(ctx.getColor(R.color.redPrimary))
    setTextColor(ctx.getColor(R.color.white))
}

