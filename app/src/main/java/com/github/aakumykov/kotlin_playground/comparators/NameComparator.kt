package com.github.aakumykov.kotlin_playground.comparators

import com.github.aakumykov.kotlin_playground.sorting_comparator.SortableItem
import com.github.aakumykov.kotlin_playground.sorting_comparator.SortingComparator

class NameComparator(reverseOrder: Boolean, foldersFirst: Boolean) : SortingComparator(reverseOrder, foldersFirst) {
    override fun compareSortableItems(item1: SortableItem, item2: SortableItem): Int {
        return item1.name.compareTo(item2.name)
    }
}