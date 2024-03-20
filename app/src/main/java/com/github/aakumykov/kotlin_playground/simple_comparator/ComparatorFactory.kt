package com.github.aakumykov.kotlin_playground.simple_comparator

import com.github.aakumykov.kotlin_playground.SortingMode

class ComparatorFactory {
    companion object {
        fun <T: Comparable<T>> createComparator(sortingMode: SortingMode): SortingComparator<T> {
            return when(sortingMode) {
                SortingMode.NAME -> NameSortingComparator()
                SortingMode.SIZE -> SizeSortingComparator()
                else -> DummySortingComparator()
            }
        }
    }
}