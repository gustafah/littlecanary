package com.gustafah.android.littlecanary.common

fun Int.aggregate() = this.toString().map { it.digitToInt() }.sum()