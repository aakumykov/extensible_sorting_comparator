package com.github.aakumykov.kotlin_playground.comparators

import com.github.aakumykov.kotlin_playground.SortableFSItem

class TimeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : BasicComparator(reverseOrder, foldersFirst) {
    override fun compareItems(item1: SortableFSItem, item2: SortableFSItem): Int {
        return item1.time.compareTo(item2.time)
    }
}