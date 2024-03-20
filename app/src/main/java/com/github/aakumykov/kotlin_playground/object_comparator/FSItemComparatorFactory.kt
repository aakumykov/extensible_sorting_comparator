package com.github.aakumykov.kotlin_playground.object_comparator

import com.github.aakumykov.kotlin_playground.SortingMode
import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.FSItem
import com.github.aakumykov.kotlin_playground.simple_comparator.NameSortingComparator

class FSItemComparatorFactory {
    /*companion object {
        fun comparator(sortingMode: SortingMode): Comparator<in FSItem> {
            return when(sortingMode) {
                SortingMode.NAME -> FSItemNameComparator()
                SortingMode.SIZE -> FSItemSizeComparator()
                else -> FSItemDummyComparator()
            }
        }
    }*/
}
