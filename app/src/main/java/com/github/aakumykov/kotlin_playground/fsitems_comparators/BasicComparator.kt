package com.github.aakumykov.kotlin_playground.fsitems_comparators

import com.github.aakumykov.kotlin_playground.extensible_sorting_comparator.ExtensibleSortingComparator
import com.github.aakumykov.kotlin_playground.fs_items.DirItem

abstract class BasicComparator(reverseOrder: Boolean, priorityItemsFirst: Boolean)
    : ExtensibleSortingComparator<SortableFSItem, DirItem>(reverseOrder, priorityItemsFirst)
{
    override fun isPriorityItem(item: SortableFSItem): Boolean = item.isDir

    override fun toPriorityItem(ordinaryItem: SortableFSItem): DirItem {
        return if (ordinaryItem.isDir) ordinaryItem as DirItem
        else throw IllegalArgumentException("Argument is not DirItem: $ordinaryItem")
    }
}