package com.github.aakumykov.kotlin_playground.simple_comparator

abstract class SortingComparator<T: Comparable<T>> : Comparator<T> {
    override fun compare(o1: T, o2: T): Int {
        return o1.compareTo(o2)
    }
}

class NameSortingComparator<T: Comparable<T>> : SortingComparator<T>() {
    /*override fun compare(o1: T, o2: T): Int {
        return 0
    }*/
}

class SizeSortingComparator<T: Comparable<T>> : SortingComparator<T>() {
    /*override fun compare(o1: T, o2: T): Int {
        return o1.compareTo(o2)
    }*/
}

class DummySortingComparator<T: Comparable<T>> : SortingComparator<T>() {
//    override fun compare(o1: T, o2: T): Int = 0
}

