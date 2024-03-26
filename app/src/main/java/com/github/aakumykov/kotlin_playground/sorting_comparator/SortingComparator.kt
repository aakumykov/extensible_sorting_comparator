package com.github.aakumykov.kotlin_playground.sorting_comparator

import android.util.Log
import com.github.aakumykov.kotlin_playground.comparators.DummyComparator
import com.github.aakumykov.kotlin_playground.comparators.NameComparator
import com.github.aakumykov.kotlin_playground.comparators.SizeComparator
import com.github.aakumykov.kotlin_playground.comparators.TimeComparator

abstract class SortingComparator(
    protected val reverseOrder: Boolean,
    protected val foldersFirst: Boolean
)
    : Comparator<SortableFSItem>
{
    //
    // Метод реализаций сортировки по разным признакам.
    //
    abstract fun compareSortableItems(item1: SortableFSItem, item2: SortableFSItem): Int


    override fun compare(o1: SortableFSItem?, o2: SortableFSItem?): Int {

        if (null == o1 || null == o2)
            return compareWithNull(o1, o2)

        return when {
            dirIsFirst(o1,o2) -> compareDirWithFile(o1,o2)
            dirIsSecond(o1,o2) -> compareFileWithDir(o1,o2)
            else -> compareOneTypeItems(o1,o2)
        }
    }


    private fun compareWithNull(item1: SortableFSItem?, item2: SortableFSItem?): Int {
        return when {
            (null == item1) -> 1
            (null == item2) -> -1
            else -> {
                Log.e(TAG, "One or both arguments of method must be null (arg1.name: ${item1.name}, arg2.name: ${item2.name}")
                return 0
            }
        }
    }


    private fun compareDirWithFile(dir: SortableFSItem, file: SortableFSItem): Int {
        return if (foldersFirst) return -1
        else compareOneTypeItems(dir,file)
    }


    private fun compareFileWithDir(file: SortableFSItem, dir: SortableFSItem): Int {
        return if (foldersFirst) 1
        else compareOneTypeItems(file,dir)
    }


    private fun compareOneTypeItems(item1: SortableFSItem, item2: SortableFSItem): Int {
        return compareSortableItems(item1, item2).let {
            if (reverseOrder) it * reverseMultiplier()
            else it
        }
    }


    private fun reverseMultiplier(): Int = if (reverseOrder) -1 else 1


    protected fun bothIsDir(item1: SortableFSItem, item2: SortableFSItem): Boolean = item1.isDir && item2.isDir

    private fun dirIsFirst(item1: SortableFSItem, item2: SortableFSItem): Boolean = item1.isDir && !item2.isDir

    private fun dirIsSecond(item1: SortableFSItem, item2: SortableFSItem): Boolean = !item1.isDir && item2.isDir


    companion object {

        val TAG: String = SortingComparator::class.java.simpleName

        fun createBuiltIn(sortingMode: SortingMode, reverseMode: Boolean, foldersFirst: Boolean): java.util.Comparator<in SortableFSItem> {
            return when(sortingMode) {
                SortingMode.NAME -> NameComparator(reverseMode,foldersFirst)
                SortingMode.SIZE -> SizeComparator(reverseMode,foldersFirst)
                SortingMode.TIME -> TimeComparator(reverseMode,foldersFirst)
                else -> DummyComparator()
            }
        }
    }

    enum class SortingMode { NAME, SIZE, TIME, UNSORTED }
}