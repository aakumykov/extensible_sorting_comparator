package com.github.aakumykov.kotlin_playground.comparators

import com.github.aakumykov.kotlin_playground.SortableFSItem
import com.github.aakumykov.kotlin_playground.sorting_comparator.SortingComparator

class DummyComparator : SortingComparator<SortableFSItem>(false, false) {
    override fun compareSortableItems(item1: SortableFSItem, item2: SortableFSItem): Int = 0
    override fun isPriorityItem(item: SortableFSItem): Boolean = false
}