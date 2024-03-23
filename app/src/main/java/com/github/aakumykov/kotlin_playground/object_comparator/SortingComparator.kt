package com.github.aakumykov.kotlin_playground.object_comparator

import android.util.Log
import com.github.aakumykov.kotlin_playground.ComparatorSortingMode
import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.FSItem

abstract class SortingComparator(
    protected val reverseOrder: Boolean,
    protected val foldersFirst: Boolean
)
    : Comparator<FSItem>
{
    abstract fun compareFSItems(item1: FSItem, item2: FSItem): Int

    /**
     * Главный метод.
     */
    override fun compare(o1: FSItem?, o2: FSItem?): Int {

        if (null == o1 || null == o2)
            return compareWithNull(o1, o2)

        return when {
            dirIsFirst(o1,o2) -> compareDirWithFile(o1,o2)
            dirIsSecond(o1,o2) -> compareFileWithDir(o1,o2)
            else -> compareCommon(o1,o2)
        }
    }


    private fun compareDirWithFile(dir: FSItem, file: FSItem): Int {
        return if (foldersFirst) return -1
        else compareCommon(dir,file)
    }

    private fun compareFileWithDir(file: FSItem, dir: FSItem): Int {
        return if (foldersFirst) 1
        else compareCommon(file,dir)
    }

    private fun compareCommon(fsItem1: FSItem, fsItem2: FSItem): Int {
        return compareFSItems(fsItem1, fsItem2).let {
            if (reverseOrder) it * reverseMultiplier()
            else it
        }/*.let {
            it * dirMultiplier(fsItem1, fsItem2)
        }*/
    }


    private fun reverseMultiplier(): Int = if (reverseOrder) -1 else 1


    /*private fun dirMultiplier(item1: FSItem, item2: FSItem): Int {
        return when {
            (item1.isDir && !item2.isDir) -> -1
            (!item1.isDir && item2.isDir) -> 1
            else -> 0
        }
    }*/


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



    class NameComparator(reverseOrder: Boolean, foldersFirst: Boolean) : SortingComparator(reverseOrder, foldersFirst) {
        override fun compareFSItems(item1: FSItem, item2: FSItem): Int {
            return item1.name.compareTo(item2.name)
        }
    }

    class SizeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : SortingComparator(reverseOrder, foldersFirst) {
        override fun compareFSItems(item1: FSItem, item2: FSItem): Int {
            return if (bothIsDir(item1, item2))
                NameComparator(reverseOrder,foldersFirst).compare(item1,item2)
            else
                item1.size.compareTo(item2.size)
        }
    }

    class TimeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : SortingComparator(reverseOrder, foldersFirst) {
        override fun compareFSItems(item1: FSItem, item2: FSItem): Int {
            return item1.time.compareTo(item2.time)
        }
    }

    class DummyComparator(reverseOrder: Boolean, foldersFirst: Boolean) : SortingComparator(reverseOrder, foldersFirst) {
        override fun compareFSItems(item1: FSItem, item2: FSItem): Int = 0
    }



    protected fun bothIsDir(item1: FSItem, item2: FSItem): Boolean = item1.isDir && item2.isDir
    private fun dirIsFirst(item1: FSItem, item2: FSItem): Boolean = item1.isDir && !item2.isDir
    private fun dirIsSecond(item1: FSItem, item2: FSItem): Boolean = !item1.isDir && item2.isDir

    companion object {
        val TAG: String = SortingComparator::class.java.simpleName
        fun create(comparatorSortingMode: ComparatorSortingMode, reverseOrder: Boolean = false, foldersFirst: Boolean = true): SortingComparator {
            return when(comparatorSortingMode) {
                ComparatorSortingMode.NAME -> NameComparator(reverseOrder, foldersFirst)
                ComparatorSortingMode.SIZE -> SizeComparator(reverseOrder, foldersFirst)
                ComparatorSortingMode.TIME -> TimeComparator(reverseOrder, foldersFirst)
                else -> DummyComparator(reverseOrder, foldersFirst)
            }
        }
    }
}