package com.github.aakumykov.kotlin_playground.extensible_sorting_comparator

import android.util.Log

abstract class ExtensibleSortingComparator<OrdinaryItemType, PriorityItemType: OrdinaryItemType>(
    protected val reverseOrder: Boolean,
    protected val priorityItemsFirst: Boolean
)
    : Comparator<OrdinaryItemType>
{
    abstract fun compareItems(item1: OrdinaryItemType, item2: OrdinaryItemType): Int

    abstract fun isPriorityItem(item: OrdinaryItemType): Boolean

    abstract fun toPriorityItem(ordinaryItem: OrdinaryItemType): PriorityItemType


    override fun compare(o1: OrdinaryItemType?, o2: OrdinaryItemType?): Int {

        if (null == o1 || null == o2)
            return compareWithNull(o1, o2)

        return when {
            priorityItemIsFirst(o1,o2) -> comparePriorityAndOrdinaryItem(o1,o2)
            priorityItemIsSecond(o1,o2) -> compareOrdinaryAndPriorityItem(o1,o2)
            else -> compareOneTypeItems(o1,o2)
        }
    }



    private fun compareWithNull(item1: OrdinaryItemType?, item2: OrdinaryItemType?): Int {
        return when {
            (null == item1) -> 1
            (null == item2) -> -1
            else -> {
                Log.e(TAG, "One or both arguments of method must be NULL (arg1: $item1, arg2: $item2")
                return 0
            }
        }
    }


    private fun comparePriorityAndOrdinaryItem(priorityItem: OrdinaryItemType, ordinaryItem: OrdinaryItemType): Int {
        return if (priorityItemsFirst) return -1
        else compareOneTypeItems(priorityItem,ordinaryItem)
    }


    private fun compareOrdinaryAndPriorityItem(ordItem: OrdinaryItemType, prItem: OrdinaryItemType): Int {
        return if (priorityItemsFirst) 1
        else compareOneTypeItems(ordItem,prItem)
    }


    private fun compareOneTypeItems(item1: OrdinaryItemType, item2: OrdinaryItemType): Int {
        return compareItems(item1, item2).let {
            if (reverseOrder) it * reverseMultiplier()
            else it
        }
    }


    private fun reverseMultiplier(): Int = if (reverseOrder) -1 else 1

    private fun priorityItemIsFirst(item1: OrdinaryItemType, item2: OrdinaryItemType): Boolean = isPriorityItem(item1) && !isPriorityItem(item2)

    private fun priorityItemIsSecond(item1: OrdinaryItemType, item2: OrdinaryItemType): Boolean = !isPriorityItem(item1) && isPriorityItem(item2)


    companion object {
        val TAG: String = ExtensibleSortingComparator::class.java.simpleName
    }
}