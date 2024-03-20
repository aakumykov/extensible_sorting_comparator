package com.github.aakumykov.kotlin_playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.aakumykov.kotlin_playground.databinding.ActivityMainBinding
import com.github.aakumykov.kotlin_playground.extensions.LogD
import com.github.aakumykov.kotlin_playground.object_comparator.FSItemComparator
import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.DirItem
import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.FSItem
import com.github.aakumykov.kotlin_playground.object_comparator.fs_items.FileItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list: List<FSItem> by lazy {
        listOf(
            DirItem("Папка 1"),
            FileItem("Файл Б", 4),
            FileItem("Файл А", 3),
            DirItem("Папка 2"),
            DirItem("А"),
            FileItem("А",5)
        )
    }
    private val folderCharacter = "\uD83D\uDCC1"
    private val fileCharacter = "\uD83D\uDCC4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sortingModeGroup.setOnCheckedChangeListener { group, checkedId -> sortAndDisplayList() }
        binding.foldersFirstSwitch.setOnCheckedChangeListener { buttonView, isChecked -> sortAndDisplayList() }
        binding.reverseOrderSwitch.setOnCheckedChangeListener { buttonView, isChecked -> sortAndDisplayList() }

        sortAndDisplayList()
    }

    private fun sortAndDisplayList() {
        list.sortedWith(FSItemComparator.create(
            sortingMode(),
            isReverseOrder(),
            isFoldersFirst()
        )).also {
            binding.textView.text = joinListToString(it)
        }
    }

    private fun joinListToString(list: List<FSItem>): String {
        return list.joinToString(separator = "\n", transform = { fsItem ->
            fsItem.name.let { name ->
                if (fsItem.isDir) "$folderCharacter $name"
                else "$fileCharacter $name (${fsItem.size} байт)"
            }
        })
    }

    private fun sortingMode(): SortingMode {
        return when(binding.sortingModeGroup.checkedRadioButtonId) {
            R.id.sortByName -> SortingMode.NAME
            R.id.sortBySize -> SortingMode.SIZE
            else -> SortingMode.UNSORTED
        }
    }

    private fun isReverseOrder(): Boolean = binding.reverseOrderSwitch.isChecked

    private fun isFoldersFirst(): Boolean = binding.foldersFirstSwitch.isChecked

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}