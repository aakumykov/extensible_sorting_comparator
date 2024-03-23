package com.github.aakumykov.kotlin_playground.fs_items

class FileItem(name: String, size: Int) : SimpleFSItem(isDir = false, name = name, size = size)