package com.iagora.wingman.core.presentation.util

import android.content.res.Resources
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton

fun EditText.setupTextWithBtn(btn: MaterialButton) {
    textChangeListener(btn)
    setOnPressEnter(btn)
}

private fun EditText.textChangeListener(btn: MaterialButton) {

    var count = 0
    this.apply {
        doOnTextChanged { _, _, _, _ ->
            btn.isEnabled = text.toString().isNotEmpty()
        }
        setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_DEL && text.isEmpty()) {
                count += 1
                if (count > 1) {
                    clearFocus(this)
                    count = 0
                }
            }

            false
        }

    }
}

fun clearFocus(editText: EditText? = null) {
    editText?.apply {
        clearFocus()
        context.hideKeyboard(this)
    }
}

private fun EditText.setOnPressEnter(
    btn: MaterialButton,
) {
    this.setOnEditorActionListener { _, actionId, event ->
        if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
            if (this.text.isNotEmpty()) {
                btn.callOnClick()
            } else {
                clearFocus(this)
            }
        }
        return@setOnEditorActionListener false
    }
}



fun Editable.convertToLong(): Long {
    val textValue = this.toString()
    return if (textValue.isEmpty() || textValue.isBlank()) 0 else textValue.toLong()
}