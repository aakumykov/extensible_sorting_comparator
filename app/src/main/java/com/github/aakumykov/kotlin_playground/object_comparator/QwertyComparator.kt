package com.github.aakumykov.kotlin_playground.object_comparator

import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.DirItem
import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.FSItem
import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.FileItem

abstract class QwertyComparator {
    abstract fun <T: FSItem> compareFSItems(item1: T, item2: T): Int

    class NameComparator : QwertyComparator() {
        override fun <T : FSItem> compareFSItems(item1: T, item2: T): Int {
            return item1.name.compareTo(item2.name)
        }
    }

    class SizeComparator : QwertyComparator() {
        override fun <T : FSItem> compareFSItems(item1: T, item2: T): Int {
            return item1.size.compareTo(item2.size)
        }
    }


    companion object {
        fun probe1() {
            val pair = Pair(DirItem("d47"), DirItem("d33"))
            NameComparator().compareFSItems(pair.first, pair.second)
            SizeComparator().compareFSItems(pair.first, pair.second)
        }
    }
}