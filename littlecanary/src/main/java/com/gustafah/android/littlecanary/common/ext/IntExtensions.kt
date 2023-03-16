package com.gustafah.android.littlecanary.common.ext

fun Int.aggregate() = this.toString().map { it.digitToInt() }.sum()