package com.github.aakumykov.kotlin_playground.fs_items

import com.github.aakumykov.kotlin_playground.SortableFSItem

abstract class SimpleFSItem(
    override val isDir: Boolean,
    override val name: String,
    override val size: Int,
    override val time: Long = fakeDate()
)
    : FSItem, SortableFSItem
{
    override fun toString(): String {
        return this.javaClass.simpleName + " { $name, $size }"
    }
}