package com.github.aakumykov.kotlin_playground.comparators

import com.github.aakumykov.kotlin_playground.SortableFSItem

class SizeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : BasicComparator(reverseOrder, foldersFirst) {

    override fun compareItems(item1: SortableFSItem, item2: SortableFSItem): Int {
        return if (isPriorityItem(item1) && isPriorityItem(item2))
            NameComparator(reverseOrder, priorityItemsFirst).compare(item1,item2)
        else
            item1.size.compareTo(item2.size)
    }
}