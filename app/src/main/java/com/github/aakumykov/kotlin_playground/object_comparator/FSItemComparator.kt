package com.github.aakumykov.kotlin_playground.object_comparator

import android.util.Log
import com.github.aakumykov.kotlin_playground.SortingMode
import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.FSItem

abstract class FSItemComparator(private val reverseOrder: Boolean) : Comparator<FSItem> {

    abstract fun compareItemsByProperty(item1: FSItem, item2: FSItem, reverseOrder: Boolean): Int

    override fun compare(o1: FSItem?, o2: FSItem?): Int {
        return if (null != o1 && null != o2) {
            compareItemsByProperty(o1, o2, reverseOrder).let {
                if (reverseOrder) it * -1
                else it
            }
        }
        else compareWithNull(o1, o2)
    }

    private fun compareWithNull(item1: FSItem?, item2: FSItem?): Int {
        return when {
            (null == item1) -> 1
            (null == item2) -> -1
            else -> {
                Log.e(TAG, "At least one argument must be null for this method. Comparision of two non-null arguments not supported!")
                return 0
            }
        }
    }


    class NameComparator(reverseOrder: Boolean) : FSItemComparator(reverseOrder) {
        override fun compareItemsByProperty(item1: FSItem, item2: FSItem, reverseOrder: Boolean): Int {
            return item1.name.compareTo(item2.name)
        }
    }

    class SizeComparator(reverseOrder: Boolean) : FSItemComparator(reverseOrder) {
        override fun compareItemsByProperty(item1: FSItem, item2: FSItem, reverseOrder: Boolean): Int {
            return item1.size.compareTo(item2.size)
        }
    }

    class DummyComparator(reverseOrder: Boolean) : FSItemComparator(reverseOrder) {
        override fun compareItemsByProperty(item1: FSItem, item2: FSItem, reverseOrder: Boolean): Int = 0
    }


    companion object {
        val TAG: String = FSItemComparator::class.java.simpleName
        fun create(sortingMode: SortingMode, reverseOrder: Boolean = false): FSItemComparator {
            return when(sortingMode) {
                SortingMode.NAME -> NameComparator(reverseOrder)
                SortingMode.SIZE -> SizeComparator(reverseOrder)
                else -> DummyComparator(reverseOrder)
            }
        }
    }
}