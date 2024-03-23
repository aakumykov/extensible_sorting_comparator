package com.github.aakumykov.kotlin_playground.sorting_comparator

class DummyComparator(reverseOrder: Boolean, foldersFirst: Boolean) : SortingComparator(reverseOrder, foldersFirst) {
    override fun compareSortableItems(item1: SortableItem, item2: SortableItem): Int = 0
}