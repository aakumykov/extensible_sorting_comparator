package com.github.aakumykov.kotlin_playground.comparators

import com.github.aakumykov.kotlin_playground.sorting_comparator.SortableFSItem
import com.github.aakumykov.kotlin_playground.sorting_comparator.SortingComparator

class DummyComparator : SortingComparator(false, false) {
    override fun compareSortableItems(item1: SortableFSItem, item2: SortableFSItem): Int = 0
}