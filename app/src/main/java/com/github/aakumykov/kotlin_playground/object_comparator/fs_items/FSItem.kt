package com.github.aakumykov.kotlin_playground.object_comparator.fs_items

interface FSItem {
    val isDir: Boolean
    val name: String
    val size: Int
    val time: Long
}