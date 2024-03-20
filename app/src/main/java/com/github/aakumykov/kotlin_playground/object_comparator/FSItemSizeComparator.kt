package com.github.aakumykov.kotlin_playground.object_comparator

import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.FSItem
import java.util.Comparator

class FSItemSizeComparator : Comparator<FSItem> {
    override fun compare(o1: FSItem?, o2: FSItem?): Int {
        return o1!!.size.compareTo(o2!!.size)
    }
}
