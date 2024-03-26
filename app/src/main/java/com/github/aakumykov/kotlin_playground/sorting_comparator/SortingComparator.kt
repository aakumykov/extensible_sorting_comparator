package com.github.aakumykov.kotlin_playground.sorting_comparator

import android.util.Log

abstract class SortingComparator<T>(
    protected val reverseOrder: Boolean,
    protected val foldersFirst: Boolean
)
    : Comparator<T>
{
    //
    // Метод реализаций сортировки по разным признакам.
    //
    abstract fun compareSortableItems(item1: T, item2: T): Int

    abstract fun isPriorityItem(item: T): Boolean


    override fun compare(o1: T?, o2: T?): Int {

        if (null == o1 || null == o2)
            return compareWithNull(o1, o2)

        return when {
            dirIsFirst(o1,o2) -> compareDirWithFile(o1,o2)
            dirIsSecond(o1,o2) -> compareFileWithDir(o1,o2)
            else -> compareOneTypeItems(o1,o2)
        }
    }


    protected fun bothIsDir(item1: SortableFSItem, item2: SortableFSItem): Boolean = item1.isDir && item2.isDir


    private fun compareWithNull(item1: T?, item2: T?): Int {
        return when {
            (null == item1) -> 1
            (null == item2) -> -1
            else -> {
                Log.e(TAG, "One or both arguments of method must be null (arg1: $item1, arg2: $item2")
                return 0
            }
        }
    }


    private fun compareDirWithFile(dir: T, file: T): Int {
        return if (foldersFirst) return -1
        else compareOneTypeItems(dir,file)
    }


    private fun compareFileWithDir(file: T, dir: T): Int {
        return if (foldersFirst) 1
        else compareOneTypeItems(file,dir)
    }


    private fun compareOneTypeItems(item1: T, item2: T): Int {
        return compareSortableItems(item1, item2).let {
            if (reverseOrder) it * reverseMultiplier()
            else it
        }
    }


    private fun reverseMultiplier(): Int = if (reverseOrder) -1 else 1

    private fun dirIsFirst(item1: T, item2: T): Boolean = isPriorityItem(item1) && !isPriorityItem(item2)

    private fun dirIsSecond(item1: T, item2: T): Boolean = !isPriorityItem(item1) && isPriorityItem(item2)


    companion object {

        val TAG: String = SortingComparator::class.java.simpleName

        /*fun <T> createBuiltIn(sortingMode: SortingMode,
                          reverseMode: Boolean,
                          foldersFirst: Boolean): java.util.Comparator<in T> {
            return when(sortingMode) {
                SortingMode.NAME -> NameComparator(reverseMode,foldersFirst)
                SortingMode.SIZE -> SizeComparator(reverseMode,foldersFirst)
                SortingMode.TIME -> TimeComparator(reverseMode,foldersFirst)
                else -> DummyComparator()
            }
        }*/
    }

}