package com.ssd.iagorawingman.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

object Other {
    fun dpToPx(dp: Int): Int {
        return ((dp * Resources.getSystem().displayMetrics.density).toInt());
    }


    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

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
        btn: MaterialButton
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
}