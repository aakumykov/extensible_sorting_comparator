package com.github.aakumykov.kotlin_playground.sorting_comparator.fs_items

import com.github.aakumykov.kotlin_playground.sorting_comparator.SortableItem

abstract class SimpleFSItem(
    override val isDir: Boolean,
    override val name: String,
    override val size: Int,
    override val time: Long = fakeDate()
)
    : FSItem, SortableItem
{
    override fun toString(): String {
        return this.javaClass.simpleName + " { $name, $size }"
    }
}