package com.github.aakumykov.extensible_sorting_comparator.fs_items_comparators

class NameComparator(reverseOrder: Boolean, foldersFirst: Boolean) : BasicComparator(reverseOrder, foldersFirst) {
    override fun compareItems(item1: SortableFSItem, item2: SortableFSItem): Int {
        return item1.name.compareTo(item2.name)
    }
}