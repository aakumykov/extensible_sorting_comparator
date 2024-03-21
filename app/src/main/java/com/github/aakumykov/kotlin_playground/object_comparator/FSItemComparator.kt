package com.github.aakumykov.kotlin_playground.object_comparator

import android.util.Log
import com.github.aakumykov.kotlin_playground.SortingMode
import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.FSItem

abstract class FSItemComparator(
    private val reverseOrder: Boolean,
    private val foldersFirst: Boolean
)
    : Comparator<FSItem>
{
    abstract fun compareItemsByProperty(item1: FSItem, item2: FSItem): Int


    override fun compare(o1: FSItem?, o2: FSItem?): Int {
        return if (null == o1 || null == o2) {
            compareWithNull(o1, o2)
        } else {
            when {
                (foldersFirst && o1.isDir && !o2.isDir) -> -1
                (foldersFirst && !o1.isDir && o2.isDir) -> 1
                else -> compareItemsByProperty(o1,o2)
            }.let {
                if (reverseOrder) it * -1
                else it
            }
        }
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



    class NameComparator(reverseOrder: Boolean, foldersFirst: Boolean) : FSItemComparator(reverseOrder, foldersFirst) {
        override fun compareItemsByProperty(item1: FSItem, item2: FSItem): Int {
            return item1.name.compareTo(item2.name)
        }
    }

    class SizeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : FSItemComparator(reverseOrder, foldersFirst) {
        override fun compareItemsByProperty(item1: FSItem, item2: FSItem): Int {
            return item1.size.compareTo(item2.size)
        }
    }

    class DummyComparator(reverseOrder: Boolean, foldersFirst: Boolean) : FSItemComparator(reverseOrder, foldersFirst) {
        override fun compareItemsByProperty(item1: FSItem, item2: FSItem): Int = 0
    }



    companion object {
        val TAG: String = FSItemComparator::class.java.simpleName
        fun create(sortingMode: SortingMode, reverseOrder: Boolean = false, foldersFirst: Boolean = true): FSItemComparator {
            return when(sortingMode) {
                SortingMode.NAME -> NameComparator(reverseOrder, foldersFirst)
                SortingMode.SIZE -> SizeComparator(reverseOrder, foldersFirst)
                else -> DummyComparator(reverseOrder, foldersFirst)
            }
        }
    }
}