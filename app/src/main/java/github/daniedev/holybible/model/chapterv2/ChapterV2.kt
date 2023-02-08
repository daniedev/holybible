package github.daniedev.holybible.model.chapterv2


import com.google.gson.annotations.SerializedName

data class ChapterV2(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("meta")
    val meta: Meta?
)