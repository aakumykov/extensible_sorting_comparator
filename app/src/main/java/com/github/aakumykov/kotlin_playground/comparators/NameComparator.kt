package com.github.aakumykov.kotlin_playground.comparators

import com.github.aakumykov.kotlin_playground.sorting_comparator.SortableFSItem
import com.github.aakumykov.kotlin_playground.sorting_comparator.SortingComparator

class NameComparator(reverseOrder: Boolean, foldersFirst: Boolean) : SortingComparator(reverseOrder, foldersFirst) {
    override fun compareSortableItems(item1: SortableFSItem, item2: SortableFSItem): Int {
        return item1.name.compareTo(item2.name)
    }
}