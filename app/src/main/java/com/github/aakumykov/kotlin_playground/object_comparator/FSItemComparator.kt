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
    abstract fun compareAsFiles(item1: FSItem, item2: FSItem): Int

    /**
     * Главный метод.
     */
    override fun compare(o1: FSItem?, o2: FSItem?): Int {

        if (null == o1 || null == o2)
            return compareWithNull(o1, o2)

        return when {
//                (foldersFirst && o1.isDir && o2.isDir) -> compareAsDirs(o1 as DirItem, o2 as DirItem)
            (foldersFirst && o1.isDir && !o2.isDir) -> -1
            (foldersFirst && !o1.isDir && o2.isDir) -> 1
            else -> compareAsFilesWrapper(o1,o2)
        }
    }

    private fun compareAsFilesWrapper(o1: FSItem, o2: FSItem): Int {
        return compareAsFiles(o1, o2).let {
            if (o1.isDir && o2.isDir) it
            else it * reverseMultiplier()
        }
    }


    private fun reverseMultiplier(): Int = if (reverseOrder) -1 else 1


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
        override fun compareAsFiles(item1: FSItem, item2: FSItem): Int {
            return item1.name.compareTo(item2.name)
        }
    }

    class SizeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : FSItemComparator(reverseOrder, foldersFirst) {
        override fun compareAsFiles(item1: FSItem, item2: FSItem): Int {
            return item1.size.compareTo(item2.size)
        }
    }

    class TimeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : FSItemComparator(reverseOrder, foldersFirst) {
        override fun compareAsFiles(item1: FSItem, item2: FSItem): Int {
            return item1.time.compareTo(item2.time)
        }
    }

    class DummyComparator(reverseOrder: Boolean, foldersFirst: Boolean) : FSItemComparator(reverseOrder, foldersFirst) {
        override fun compareAsFiles(item1: FSItem, item2: FSItem): Int = 0
    }



    companion object {
        val TAG: String = FSItemComparator::class.java.simpleName
        fun create(sortingMode: SortingMode, reverseOrder: Boolean = false, foldersFirst: Boolean = true): FSItemComparator {
            return when(sortingMode) {
                SortingMode.NAME -> NameComparator(reverseOrder, foldersFirst)
                SortingMode.SIZE -> SizeComparator(reverseOrder, foldersFirst)
                SortingMode.TIME -> TimeComparator(reverseOrder, foldersFirst)
                else -> DummyComparator(reverseOrder, foldersFirst)
            }
        }
    }
}