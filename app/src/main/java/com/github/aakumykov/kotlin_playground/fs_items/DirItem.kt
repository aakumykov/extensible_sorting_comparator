package com.github.aakumykov.kotlin_playground.fs_items

class DirItem(
    name: String,
    val itemsCount: Int
) : SimpleFSItem(isDir = true, name = name, size = 0) {

    override val size get() = itemsCount

    override fun toString(): String {
        return super.toString() + ", содержит $itemsCount элементов"
    }
}