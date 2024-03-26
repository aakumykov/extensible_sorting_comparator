package com.github.aakumykov.kotlin_playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.aakumykov.kotlin_playground.databinding.ActivityMainBinding
import com.github.aakumykov.kotlin_playground.fs_items.DirItem
import com.github.aakumykov.kotlin_playground.fs_items.FileItem
import com.github.aakumykov.kotlin_playground.sorting_comparator.SortableFSItem
import com.github.aakumykov.kotlin_playground.sorting_comparator.SortingComparator

class MainActivity : AppCompatActivity() {

    private val list: List<SortableFSItem> by lazy { listOf(
            DirItem("Папка 1"),
            FileItem("Файл Б", 4),
            FileItem("Файл А", 3),
            DirItem("Папка 2"),
            DirItem("А1"),
            FileItem("А2",5)
    )}

    private val folderCharacter = "\uD83D\uDCC1"
    private val fileCharacter = "\uD83D\uDCC4"
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sortingModeGroup.setOnCheckedChangeListener { _, _ -> sortAndDisplayList() }
        binding.foldersFirstSwitch.setOnCheckedChangeListener { _, _ -> sortAndDisplayList() }
        binding.reverseOrderSwitch.setOnCheckedChangeListener { _, _ -> sortAndDisplayList() }

        sortAndDisplayList()
    }

    private fun sortAndDisplayList() {
        list.sortedWith(
            SortingComparator.createBuiltIn(sortingMode(), isReverseOrder(), isFoldersFirst())
        ).also {
            binding.textView.text = joinListToString(it)
        }
    }

    private fun joinListToString(list: List<SortableFSItem>): String {
        return list.joinToString(separator = "\n", transform = { fsItem ->
            fsItem.name.let { name ->
                if (fsItem.isDir) "$folderCharacter $name"
                else "$fileCharacter $name (${fsItem.size} байт)"
            }
        })
    }

    private fun sortingMode(): SortingComparator.SortingMode {
        return when(binding.sortingModeGroup.checkedRadioButtonId) {
            R.id.sortByName -> SortingComparator.SortingMode.NAME
            R.id.sortBySize -> SortingComparator.SortingMode.SIZE
            R.id.sortByTime -> SortingComparator.SortingMode.TIME
            else -> SortingComparator.SortingMode.UNSORTED
        }
    }

    private fun isReverseOrder(): Boolean = binding.reverseOrderSwitch.isChecked

    private fun isFoldersFirst(): Boolean = binding.foldersFirstSwitch.isChecked
}