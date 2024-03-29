package com.ssd.iagorawingman.utils

import android.widget.TextView
import java.text.DecimalFormat

object FormatCurrency {

    fun TextView.formatPrice(value: String) {
        this.text = getCurrencyRp(java.lang.Double.parseDouble(value))
    }

    fun getCurrencyRp (price: Double): String {
        val format = DecimalFormat("#,###,###")
        return "Rp " + format.format(price).replace(",".toRegex(), ".")
    }
}