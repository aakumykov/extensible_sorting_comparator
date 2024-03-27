package com.github.aakumykov.kotlin_playground.fs_items_comparators

interface SortableFSItem {
    val isDir: Boolean
    val name: String
    val size: Int
    val time: Long
}