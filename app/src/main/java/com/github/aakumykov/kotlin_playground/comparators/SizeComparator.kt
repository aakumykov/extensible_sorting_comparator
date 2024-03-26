package com.github.aakumykov.kotlin_playground.comparators

import com.github.aakumykov.kotlin_playground.sorting_comparator.SortableFSItem
import com.github.aakumykov.kotlin_playground.sorting_comparator.SortingComparator

class SizeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : SortingComparator(reverseOrder, foldersFirst) {
    override fun compareSortableItems(item1: SortableFSItem, item2: SortableFSItem): Int {
        return if (bothIsDir(item1, item2))
            NameComparator(reverseOrder, foldersFirst).compare(item1,item2)
        else
            item1.size.compareTo(item2.size)
    }
}