package com.github.aakumykov.kotlin_playground.fs_items_comparators

class DummyComparator : BasicComparator(false, false) {
    override fun compareItems(item1: SortableFSItem, item2: SortableFSItem): Int = 0
}