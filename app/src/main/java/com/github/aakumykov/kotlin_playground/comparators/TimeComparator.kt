package com.github.aakumykov.kotlin_playground.comparators

import com.github.aakumykov.kotlin_playground.sorting_comparator.SortableFSItem
import com.github.aakumykov.kotlin_playground.sorting_comparator.SortingComparator

class TimeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : SortingComparator(reverseOrder, foldersFirst) {
    override fun compareSortableItems(item1: SortableFSItem, item2: SortableFSItem): Int {
        return item1.time.compareTo(item2.time)
    }
}