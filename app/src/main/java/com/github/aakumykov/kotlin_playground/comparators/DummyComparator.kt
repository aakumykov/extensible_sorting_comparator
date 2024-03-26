package com.github.aakumykov.kotlin_playground.comparators

import com.github.aakumykov.kotlin_playground.SortableFSItem

class DummyComparator : BasicComparator(false, false) {
    override fun compareItems(item1: SortableFSItem, item2: SortableFSItem): Int = 0
}