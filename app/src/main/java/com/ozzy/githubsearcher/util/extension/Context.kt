package com.ozzy.githubsearcher.util.extension

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager

/**
 * Created by Oğuzhan Karacan on 1.01.2021.
 */
fun Context?.hideKeyboard() {
    try {
        val inputMethodManager =
            this!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            (this as Activity).currentFocus!!.windowToken,
            0
        )
    } catch (e: Exception) {
        if (e.message != null) {
            Log.d("KeyboardError", e.message.toString())
        }
    }
}