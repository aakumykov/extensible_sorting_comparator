package com.github.aakumykov.extensible_sorting_comparator.fs_items

interface FSItem {
    val isDir: Boolean
    val name: String
    val size: Int
    val time: Long
}