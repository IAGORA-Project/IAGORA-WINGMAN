package com.iagora.wingman.gallery.presentation.util

import android.content.res.Configuration
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart

fun ViewGroup.circularReveal(button: ImageButton) {
    ViewAnimationUtils.createCircularReveal(
        this,
        button.x.toInt() + button.width / 2,
        button.y.toInt() + button.height / 2,
        0f,
        if (button.context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) this.width.toFloat() else this.height.toFloat()
    ).apply {
        duration = 500
        doOnStart { visibility = View.VISIBLE }
    }.start()
}

fun ViewGroup.circularClose(button: ImageButton, action: () -> Unit = {}) {
    ViewAnimationUtils.createCircularReveal(
        this,
        button.x.toInt() + button.width / 2,
        button.y.toInt() + button.height / 2,
        if (button.context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) this.width.toFloat() else this.height.toFloat(),
        0f
    ).apply {
        duration = 500
        doOnStart { action() }
        doOnEnd { visibility = View.GONE }
    }.start()
}
