package com.github.aakumykov.kotlin_playground

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