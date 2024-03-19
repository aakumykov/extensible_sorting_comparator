package com.github.aakumykov.kotlin_playground

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val list1: List<String> = listOf(
            "Москва", "Санкт-Петербург", "Кемерово"
        )
        Log.d(TAG, list1.toString())

        val list1sorted = list1.sortedWith(ComparatorFactory.createComparator(SortingMode.NAME))

        Log.d(TAG, list1sorted.toString())
    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}