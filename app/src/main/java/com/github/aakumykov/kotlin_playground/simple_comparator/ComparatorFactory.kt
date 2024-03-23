package com.github.aakumykov.kotlin_playground.simple_comparator

import com.github.aakumykov.kotlin_playground.ComparatorSortingMode

class ComparatorFactory {
    companion object {
        fun <T: Comparable<T>> createComparator(comparatorSortingMode: ComparatorSortingMode): SortingComparator<T> {
            return when(comparatorSortingMode) {
                ComparatorSortingMode.NAME -> NameSortingComparator()
                ComparatorSortingMode.SIZE -> SizeSortingComparator()
                else -> DummySortingComparator()
            }
        }
    }
}