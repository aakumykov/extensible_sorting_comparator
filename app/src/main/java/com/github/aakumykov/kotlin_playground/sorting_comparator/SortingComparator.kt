package com.github.aakumykov.kotlin_playground.sorting_comparator

import android.util.Log

abstract class SortingComparator(
    protected val reverseOrder: Boolean,
    protected val foldersFirst: Boolean
)
    : Comparator<SortableItem>
{
    //
    // Метод реализаций сортировки по разным признакам.
    //
    abstract fun compareSortableItems(item1: SortableItem, item2: SortableItem): Int


    override fun compare(o1: SortableItem?, o2: SortableItem?): Int {

        if (null == o1 || null == o2)
            return compareWithNull(o1, o2)

        return when {
            dirIsFirst(o1,o2) -> compareDirWithFile(o1,o2)
            dirIsSecond(o1,o2) -> compareFileWithDir(o1,o2)
            else -> compareOneTypeItems(o1,o2)
        }
    }


    private fun compareWithNull(item1: SortableItem?, item2: SortableItem?): Int {
        return when {
            (null == item1) -> 1
            (null == item2) -> -1
            else -> {
                Log.e(TAG, "At least one argument must be null for this method. Comparision of two non-null arguments not supported!")
                return 0
            }
        }
    }


    private fun compareDirWithFile(dir: SortableItem, file: SortableItem): Int {
        return if (foldersFirst) return -1
        else compareOneTypeItems(dir,file)
    }


    private fun compareFileWithDir(file: SortableItem, dir: SortableItem): Int {
        return if (foldersFirst) 1
        else compareOneTypeItems(file,dir)
    }


    private fun compareOneTypeItems(item1: SortableItem, item2: SortableItem): Int {
        return compareSortableItems(item1, item2).let {
            if (reverseOrder) it * reverseMultiplier()
            else it
        }
    }


    private fun reverseMultiplier(): Int = if (reverseOrder) -1 else 1



    class NameComparator(reverseOrder: Boolean, foldersFirst: Boolean) : SortingComparator(reverseOrder, foldersFirst) {
        override fun compareSortableItems(item1: SortableItem, item2: SortableItem): Int {
            return item1.name.compareTo(item2.name)
        }
    }

    class SizeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : SortingComparator(reverseOrder, foldersFirst) {
        override fun compareSortableItems(item1: SortableItem, item2: SortableItem): Int {
            return if (bothIsDir(item1, item2))
                NameComparator(reverseOrder,foldersFirst).compare(item1,item2)
            else
                item1.size.compareTo(item2.size)
        }
    }

    class TimeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : SortingComparator(reverseOrder, foldersFirst) {
        override fun compareSortableItems(item1: SortableItem, item2: SortableItem): Int {
            return item1.time.compareTo(item2.time)
        }
    }

    class DummyComparator(reverseOrder: Boolean, foldersFirst: Boolean) : SortingComparator(reverseOrder, foldersFirst) {
        override fun compareSortableItems(item1: SortableItem, item2: SortableItem): Int = 0
    }



    protected fun bothIsDir(item1: SortableItem, item2: SortableItem): Boolean = item1.isDir && item2.isDir
    private fun dirIsFirst(item1: SortableItem, item2: SortableItem): Boolean = item1.isDir && !item2.isDir
    private fun dirIsSecond(item1: SortableItem, item2: SortableItem): Boolean = !item1.isDir && item2.isDir


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