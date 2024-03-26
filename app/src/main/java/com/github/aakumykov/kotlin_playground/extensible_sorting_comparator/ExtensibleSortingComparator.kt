package com.github.aakumykov.kotlin_playground.extensible_sorting_comparator

import android.util.Log

abstract class ExtensibleSortingComparator<T>(
    protected val reverseOrder: Boolean,
    protected val priorityItemsFirst: Boolean
)
    : Comparator<T>
{
    abstract fun compareItems(item1: T, item2: T): Int

    abstract fun isPriorityItem(item: T): Boolean

    override fun compare(o1: T?, o2: T?): Int {

        if (null == o1 || null == o2)
            return compareWithNull(o1, o2)

        return when {
            priorityItemIsFirst(o1,o2) -> comparePriorityAndOrdinaryItem(o1,o2)
            priorityItemIsSecond(o1,o2) -> compareOrdinaryAndPriorityItem(o1,o2)
            else -> compareOneTypeItems(o1,o2)
        }
    }



    private fun compareWithNull(item1: T?, item2: T?): Int {
        return when {
            (null == item1) -> 1
            (null == item2) -> -1
            else -> {
                Log.e(TAG, "One or both arguments of method must be NULL (arg1: $item1, arg2: $item2")
                return 0
            }
        }
    }


    private fun comparePriorityAndOrdinaryItem(priorityItem: T, ordinaryItem: T): Int {
        return if (priorityItemsFirst) return -1
        else compareOneTypeItems(priorityItem,ordinaryItem)
    }


    private fun compareOrdinaryAndPriorityItem(ordItem: T, prItem: T): Int {
        return if (priorityItemsFirst) 1
        else compareOneTypeItems(ordItem,prItem)
    }


    private fun compareOneTypeItems(item1: T, item2: T): Int {
        return compareItems(item1, item2).let {
            if (reverseOrder) it * reverseMultiplier()
            else it
        }
    }


    private fun reverseMultiplier(): Int = if (reverseOrder) -1 else 1

    private fun priorityItemIsFirst(item1: T, item2: T): Boolean = isPriorityItem(item1) && !isPriorityItem(item2)

    private fun priorityItemIsSecond(item1: T, item2: T): Boolean = !isPriorityItem(item1) && isPriorityItem(item2)


    companion object {
        val TAG: String = ExtensibleSortingComparator::class.java.simpleName
    }
}