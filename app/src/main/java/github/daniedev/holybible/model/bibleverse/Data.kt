package github.daniedev.holybible.model.bibleverse


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("bibleId")
    val bibleId: String?,
    @SerializedName("bookId")
    val bookId: String?,
    @SerializedName("chapterId")
    val chapterId: String?,
    @SerializedName("content")
    val content: List<Content>,
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("next")
    val next: Next?,
    @SerializedName("orgId")
    val orgId: String?,
    @SerializedName("previous")
    val previous: Previous?,
    @SerializedName("reference")
    val reference: String?,
    @SerializedName("verseCount")
    val verseCount: Int?
)