package com.github.aakumykov.kotlin_playground.comparators

import com.github.aakumykov.kotlin_playground.sorting_comparator.SortableItem
import com.github.aakumykov.kotlin_playground.sorting_comparator.SortingComparator

class SizeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : SortingComparator(reverseOrder, foldersFirst) {
    override fun compareSortableItems(item1: SortableItem, item2: SortableItem): Int {
        return if (bothIsDir(item1, item2))
            NameComparator(reverseOrder, foldersFirst).compare(item1,item2)
        else
            item1.size.compareTo(item2.size)
    }
}