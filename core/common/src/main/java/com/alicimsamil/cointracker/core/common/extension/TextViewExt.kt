package com.alicimsamil.cointracker.core.common.extension

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat

fun TextView.setTextColor(context: Context, color: Int) {
    this.setTextColor(
        ContextCompat.getColor(
            context,
            color
        )
    )
}