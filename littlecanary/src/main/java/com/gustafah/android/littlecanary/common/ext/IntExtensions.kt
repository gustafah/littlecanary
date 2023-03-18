package com.gustafah.android.littlecanary.common.ext

import android.text.InputType

fun Int.aggregate() = this.toString().map { it.digitToInt() }.sum()

fun Int.forceNumbers() = this or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD