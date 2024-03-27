package com.github.aakumykov.extensible_sorting_comparator.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.aakumykov.extensible_sorting_comparator.R
import com.github.aakumykov.extensible_sorting_comparator.databinding.ActivityMainBinding
import com.github.aakumykov.extensible_sorting_comparator.fs_items.DirItem
import com.github.aakumykov.extensible_sorting_comparator.fs_items.FileItem
import com.github.aakumykov.extensible_sorting_comparator.fs_items_comparators.DummyComparator
import com.github.aakumykov.extensible_sorting_comparator.fs_items_comparators.NameComparator
import com.github.aakumykov.extensible_sorting_comparator.fs_items_comparators.SizeComparator
import com.github.aakumykov.extensible_sorting_comparator.fs_items_comparators.SortableFSItem
import com.github.aakumykov.extensible_sorting_comparator.fs_items_comparators.TimeComparator
import com.github.javafaker.Faker
import java.util.Locale
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val list: List<SortableFSItem> by lazy { listOf(
            DirItem(randomName()),
            FileItem(randomName(), randomSize()),
            FileItem(randomName(), randomSize()),
            DirItem(randomName()),
            DirItem(randomName()),
            FileItem(randomName(),randomSize())
    )}

    private fun randomName(): String = faker.animal().name().replaceFirstChar { c -> c.uppercaseChar() }
    private fun randomSize(): Int = Random.nextInt(10)
    private val faker: Faker = Faker(Locale.getDefault())
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
            sortingComparator(sortingMode(), isReverseOrder(), isFoldersFirst())
        ).also {
            binding.textView.text = joinListToString(it)
        }
    }

    private fun sortingComparator(sortingMode: SortingMode,
                                  reverseOrder: Boolean,
                                  foldersFirst: Boolean): com.github.aakumykov.extensible_sorting_comparator.ExtensibleSortingComparator<SortableFSItem> {
        return when(sortingMode) {
            SortingMode.NAME -> NameComparator(reverseOrder, foldersFirst)
            SortingMode.TIME -> TimeComparator(reverseOrder, foldersFirst)
            SortingMode.SIZE -> SizeComparator(reverseOrder, foldersFirst)
            else -> DummyComparator()
        }
    }

    private fun joinListToString(list: List<SortableFSItem>): String {
        return list.joinToString(separator = "\n", transform = { fsItem ->
            fsItem.name.let { name ->
                if (fsItem.isDir) "$folderCharacter $name"
                else "$fileCharacter $name (${fsItem.size} ${getString(R.string.bytes)})"
            }
        })
    }

    private fun sortingMode(): SortingMode {
        return when(binding.sortingModeGroup.checkedRadioButtonId) {
            R.id.sortByName -> SortingMode.NAME
            R.id.sortBySize -> SortingMode.SIZE
            R.id.sortByTime -> SortingMode.TIME
            else -> SortingMode.UNSORTED
        }
    }

    private fun isReverseOrder(): Boolean = binding.reverseOrderSwitch.isChecked

    private fun isFoldersFirst(): Boolean = binding.foldersFirstSwitch.isChecked
}