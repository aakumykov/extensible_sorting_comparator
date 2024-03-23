package com.github.aakumykov.kotlin_playground.comparators

import com.github.aakumykov.kotlin_playground.sorting_comparator.SortableItem
import com.github.aakumykov.kotlin_playground.sorting_comparator.SortingComparator

class DummyComparator : SortingComparator(false, false) {
    override fun compareSortableItems(item1: SortableItem, item2: SortableItem): Int = 0
}