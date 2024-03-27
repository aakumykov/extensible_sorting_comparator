package com.github.aakumykov.kotlin_playground.fs_items_comparators

import com.github.aakumykov.kotlin_playground.extensible_sorting_comparator.ExtensibleSortingComparator

abstract class BasicComparator(reverseOrder: Boolean, priorityItemsFirst: Boolean)
    : ExtensibleSortingComparator<SortableFSItem>(reverseOrder, priorityItemsFirst)
{
    override fun isPriorityItem(item: SortableFSItem): Boolean = item.isDir
}