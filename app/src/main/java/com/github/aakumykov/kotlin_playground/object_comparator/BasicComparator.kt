package com.github.aakumykov.kotlin_playground.object_comparator

import android.util.Log

abstract class BasicComparator<T> : Comparator<T>{

    protected fun compareWithNull(o1: Comparable<T>?, o2: Comparable<T>?): Int {
        return when {
            (null == o1) -> 1
            (null == o2) -> -1
            else -> {
                Log.e(TAG, "At least one argument must be null for this method. Comparision of two non-null arguments not supported!")
                0
            }
        }
    }

    companion object {
        val TAG: String = BasicComparator::class.java.simpleName
    }
}
