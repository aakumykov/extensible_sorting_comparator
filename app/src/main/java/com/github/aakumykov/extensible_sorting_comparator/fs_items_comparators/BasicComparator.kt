package com.github.aakumykov.extensible_sorting_comparator.fs_items_comparators

import com.github.aakumykov.extensible_sorting_comparator.ExtensibleSortingComparator

abstract class BasicComparator(reverseOrder: Boolean, priorityItemsFirst: Boolean)
    : ExtensibleSortingComparator<SortableFSItem>(reverseOrder, priorityItemsFirst)
{
    override fun isPriorityItem(item: SortableFSItem): Boolean = item.isDir
}