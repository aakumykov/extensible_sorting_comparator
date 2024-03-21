package com.github.aakumykov.kotlin_playground.object_comparator.fs_items

import android.text.format.DateUtils
import java.util.Date
import kotlin.random.Random

fun fakeDate(): Long {

    val currentTime: Long = Date().time
    val randomInterval = DateUtils.DAY_IN_MILLIS * Random.nextInt(10)

    return when (Random.nextInt(1,10)) {
        in 1..3 -> currentTime
        in 4..6 -> currentTime - randomInterval
        else -> currentTime + randomInterval
    }
}