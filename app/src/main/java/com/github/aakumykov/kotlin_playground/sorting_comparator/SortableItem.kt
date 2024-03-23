package com.github.aakumykov.kotlin_playground.sorting_comparator

interface SortableItem {
    val isDir: Boolean // TODO: каталог-не каталог - это признак конкретной реализации, хорошо бы его вынести в класс-потомок
    val name: String
    val size: Int
    val time: Long
}