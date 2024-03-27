package com.github.aakumykov.kotlin_playground.fs_items_comparators

class TimeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : BasicComparator(reverseOrder, foldersFirst) {
    override fun compareItems(item1: SortableFSItem, item2: SortableFSItem): Int {
        return item1.time.compareTo(item2.time)
    }
}