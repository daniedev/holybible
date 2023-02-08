package github.daniedev.holybible.model.bibleverse


import com.google.gson.annotations.SerializedName

data class BibleVerse(
    @SerializedName("data")
    val `data`: Data
)