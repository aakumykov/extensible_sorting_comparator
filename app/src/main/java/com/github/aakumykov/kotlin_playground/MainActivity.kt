package com.github.aakumykov.kotlin_playground

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.aakumykov.kotlin_playground.extensions.LogD
import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.DirItem
import com.github.aakumykov.kotlin_playground.object_comparator.FSItemComparatorFactory
import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.FileItem
import com.github.aakumykov.kotlin_playground.simple_comparator.ComparatorFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val list1: List<String> = listOf("Москва", "Санкт-Петербург", "Кемерово")
//        Log.d(TAG, list1.toString())
//
//        val list1sorted = list1.sortedWith(ComparatorFactory.createComparator(SortingMode.NAME))
//        Log.d(TAG, list1sorted.toString())

        listOf(
            DirItem("dir1"), FileItem("file2", 4),
            FileItem("file_a", 3), DirItem("dir5")
        ).let {
            LogD(it.toString())
            it
        }.also {
            LogD(it.sortedWith(FSItemComparatorFactory.comparator(SortingMode.NAME)).toString())
        }
    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}