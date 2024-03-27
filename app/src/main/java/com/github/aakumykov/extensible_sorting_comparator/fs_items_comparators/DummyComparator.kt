package com.github.aakumykov.extensible_sorting_comparator.fs_items_comparators

class DummyComparator : BasicComparator(false, false) {
    override fun compareItems(item1: SortableFSItem, item2: SortableFSItem): Int = 0
}