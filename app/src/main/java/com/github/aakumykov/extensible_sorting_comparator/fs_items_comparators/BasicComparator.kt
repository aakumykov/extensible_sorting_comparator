package com.github.aakumykov.extensible_sorting_comparator.fs_items_comparators

abstract class BasicComparator(reverseOrder: Boolean, priorityItemsFirst: Boolean)
    : com.github.aakumykov.extensible_sorting_comparator.ExtensibleSortingComparator<SortableFSItem>(reverseOrder, priorityItemsFirst)
{
    override fun isPriorityItem(item: SortableFSItem): Boolean = item.isDir
}