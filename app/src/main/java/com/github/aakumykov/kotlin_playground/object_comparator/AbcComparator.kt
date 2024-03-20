package com.github.aakumykov.kotlin_playground.object_comparator

import android.util.Log
import com.github.aakumykov.kotlin_playground.SortingMode
import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.FSItem

abstract class AbcComparator : Comparator<FSItem> {

    protected fun compareWithNull(item1: FSItem?, item2: FSItem?): Int {
        return when {
            (null == item1) -> 1
            (null == item2) -> -1
            else -> {
                Log.e(BasicComparator.TAG, "At least one argument must be null for this method. Comparision of two non-null arguments not supported!")
                return 0
            }
        }
    }

    class NameComparator : AbcComparator() {
        override fun compare(o1: FSItem?, o2: FSItem?): Int {
            return if (null == o1 || null == o2) compareWithNull(o1,o2)
            else {
                o1.name.compareTo(o2.name)
            }
        }
    }

    class SizeComparator : AbcComparator() {
        override fun compare(o1: FSItem?, o2: FSItem?): Int {
            return if (null == o1 || null == o2) compareWithNull(o1,o2)
            else {
                o1.size.compareTo(o2.size)
            }
        }
    }

    class DummyComparator : AbcComparator() {
        override fun compare(o1: FSItem?, o2: FSItem?): Int = 0
    }


    companion object {
        fun create(sortingMode: SortingMode): AbcComparator {
            return when(sortingMode) {
                SortingMode.NAME -> NameComparator()
                SortingMode.SIZE -> SizeComparator()
                else -> DummyComparator()
            }
        }
    }
}