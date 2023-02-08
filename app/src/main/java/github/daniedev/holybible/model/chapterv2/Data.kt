package github.daniedev.holybible.model.chapterv2


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("bibleId")
    val bibleId: String?,
    @SerializedName("bookId")
    val bookId: String?,
    @SerializedName("content")
    val content: List<Content>?,
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("next")
    val next: Next?,
    @SerializedName("number")
    val number: String?,
    @SerializedName("parallels")
    val parallels: List<Parallel>?,
    @SerializedName("previous")
    val previous: Previous?,
    @SerializedName("reference")
    val reference: String?,
    @SerializedName("verseCount")
    val verseCount: Int?
)