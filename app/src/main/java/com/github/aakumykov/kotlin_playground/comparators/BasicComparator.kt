package com.github.aakumykov.kotlin_playground.comparators

import com.github.aakumykov.kotlin_playground.SortableFSItem
import com.github.aakumykov.kotlin_playground.sorting_comparator.SortingComparator

abstract class BasicComparator(reverseOrder: Boolean, priorityItemsFirst: Boolean)
    : SortingComparator<SortableFSItem>(reverseOrder, priorityItemsFirst)
{
    override fun isPriorityItem(item: SortableFSItem): Boolean = item.isDir
}