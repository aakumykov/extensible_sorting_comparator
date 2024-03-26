package com.github.aakumykov.kotlin_playground.fsitems_comparators

class SizeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : BasicComparator(reverseOrder, foldersFirst) {

    override fun compareItems(item1: SortableFSItem, item2: SortableFSItem): Int {
        return item1.size.compareTo(item2.size)
    }
}