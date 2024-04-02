package com.github.aakumykov.extensible_sorting_comparator.fs_items

class FileItem(name: String, size: Long) : SimpleFSItem(isDir = false, name = name, size = size)