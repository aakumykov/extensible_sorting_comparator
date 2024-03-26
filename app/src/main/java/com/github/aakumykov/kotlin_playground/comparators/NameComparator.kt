package com.github.aakumykov.kotlin_playground.comparators

import com.github.aakumykov.kotlin_playground.SortableFSItem

class NameComparator(reverseOrder: Boolean, foldersFirst: Boolean) : BasicComparator(reverseOrder, foldersFirst) {
    override fun compareItems(item1: SortableFSItem, item2: SortableFSItem): Int {
        return item1.name.compareTo(item2.name)
    }
}