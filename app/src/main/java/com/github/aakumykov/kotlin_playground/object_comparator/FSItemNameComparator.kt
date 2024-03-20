package com.github.aakumykov.kotlin_playground.object_comparator

import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.FSItem

class FSItemNameComparator : Comparator<FSItem> {
    override fun compare(o1: FSItem?, o2: FSItem?): Int {
        return o1!!.name.compareTo(o2!!.name)
    }
}
