package github.daniedev.holybible.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BibleVerseItem(var verseInEnglish: String = "", var verseInTamil: String = "", val verseId: String): Parcelable
