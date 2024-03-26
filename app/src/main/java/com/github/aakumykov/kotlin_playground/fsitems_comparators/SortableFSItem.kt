package com.github.aakumykov.kotlin_playground.fsitems_comparators

interface SortableFSItem {
    val isDir: Boolean
    val name: String
    val size: Int
    val time: Long
}