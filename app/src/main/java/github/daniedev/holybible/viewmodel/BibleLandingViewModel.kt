package github.daniedev.holybible.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.daniedev.holybible.model.BibleVerseItem
import github.daniedev.holybible.model.books.Book
import github.daniedev.holybible.model.books.Books
import github.daniedev.holybible.model.chapter.Chapter
import github.daniedev.holybible.model.chapter.Content
import github.daniedev.holybible.service.BibleService
import github.daniedev.holybible.service.retrofit
import github.daniedev.holybible.util.constants.BibleVersions.IRV_TAMIL
import github.daniedev.holybible.util.constants.BibleVersions.KJV_ENGLISH
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class BibleLandingViewModel : ViewModel() {
    val verse = mutableStateOf("")
    private var bibleVerseMap : MutableMap<String, BibleVerseItem> = hashMapOf()
    private lateinit var listOfAvailableBooks : List<String>

    init {
        viewModelScope.launch {
            val bibleService = retrofit.create(BibleService::class.java)
            val availableBooks = bibleService.getAllBooks(KJV_ENGLISH)
            listOfAvailableBooks = availableBooks.books?.let { book -> book.map { it.name } } ?: emptyList()
        }
    }

    suspend fun getBibleVerseItems(chapterId: String) : List<BibleVerseItem> {
        val bibleVerseItemList = viewModelScope.async {
            val bibleService = retrofit.create(BibleService::class.java)
            val chapter: Chapter = bibleService.getChapter(IRV_TAMIL, chapterId, "json", KJV_ENGLISH)
            verse.value = chapter.data?.content?.get(0)?.items?.get(0)?.text ?: ""
            chapter.data?.content?.let { contents ->
                addVerseFromContentIntoMap(contents, IRV_TAMIL)
            }
            chapter.data?.parallels?.get(0)?.content?.let {
                contents ->
                addVerseFromContentIntoMap(contents, KJV_ENGLISH)
            }
            bibleVerseMap = bibleVerseMap.toSortedMap(compareBy {
                val index = it.lastIndexOf('.')
                val numberString = it.substring(index + 1)
                numberString.toInt()
            })
            return@async bibleVerseMap.values.toList()
        }
        return bibleVerseItemList.await()
    }

    private fun addVerseFromContentIntoMap(contents: List<Content>, version: String) {
        for (content in contents) {
            content.items?.let { items ->
                for (item in items) {
                    if (item.type == "text" && item.attrs?.verseId != null) {
                        val verseId = item.attrs.verseId
                        if (bibleVerseMap.containsKey(verseId)) {
                            bibleVerseMap[verseId]?.let { bibleVerseItem ->
                                bibleVerseItem.apply {
                                    if (version == IRV_TAMIL) verseInTamil = verseInTamil.plus(item.text).replace(":", ",")
                                    else verseInEnglish = verseInEnglish.plus(item.text).replace(":", ",")
                                }
                            }
                        } else {
                            val bibleVerseItem = BibleVerseItem(verseId = verseId).apply {
                                if (version == IRV_TAMIL) verseInTamil = item.text.replace(":", ",")
                            else verseInEnglish = item.text.replace(":", ",")
                            }
                            bibleVerseMap[verseId] = bibleVerseItem
                        }
                    }
                }
            }
        }
    }
}