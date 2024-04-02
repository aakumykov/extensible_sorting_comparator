package com.github.aakumykov.extensible_sorting_comparator.fs_items

import com.github.aakumykov.extensible_sorting_comparator.fs_items_comparators.SortableFSItem

abstract class SimpleFSItem(
    override val isDir: Boolean,
    override val name: String,
    override val size: Long,
    override val time: Long = fakeDate()
)
    : FSItem, SortableFSItem
{
    override fun toString(): String {
        return this.javaClass.simpleName + " { $name, $size }"
    }
}