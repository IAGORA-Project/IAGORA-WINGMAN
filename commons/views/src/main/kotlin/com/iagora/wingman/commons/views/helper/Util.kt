package com.iagora.wingman.commons.views.helper

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton

object Util {
    fun dpToPx(dp: Int): Int {
        return ((dp * Resources.getSystem().displayMetrics.density).toInt())
    }


    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun View.hide() {
        this.isVisible = false
    }

    fun View.show() {
        this.isVisible = true
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
    fun ViewPager2.reduceDragSensitivity() {
        val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
        recyclerViewField.isAccessible = true
        val recyclerView = recyclerViewField.get(this) as RecyclerView

        val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
        touchSlopField.isAccessible = true
        val touchSlop = touchSlopField.get(recyclerView) as Int
        touchSlopField.set(recyclerView, touchSlop*2)       // "2" was obtained experimentally
    }
}