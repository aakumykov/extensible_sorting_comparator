package com.github.aakumykov.extensible_sorting_comparator.fs_items_comparators

class SizeComparator(reverseOrder: Boolean, foldersFirst: Boolean) : BasicComparator(reverseOrder, foldersFirst) {

    override fun compareItems(item1: SortableFSItem, item2: SortableFSItem): Int {
        return if (isPriorityItem(item1) && isPriorityItem(item2))
            NameComparator(reverseOrder, priorityItemsFirst).compare(item1,item2)
        else
            item1.size.compareTo(item2.size)
    }
}