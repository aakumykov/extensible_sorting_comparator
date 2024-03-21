package com.github.aakumykov.kotlin_playground.object_comparator.fs_items

abstract class SimpleFSItem(
    override val isDir: Boolean,
    override val name: String,
    override val size: Int,
    override val time: Long = fakeDate()
) : FSItem {
    override fun toString(): String {
        return this.javaClass.simpleName + " { $name, $size }"
    }
}