package github.daniedev.holybible.model.chapterv2


import com.google.gson.annotations.SerializedName

data class Parallel(
    @SerializedName("bibleId")
    val bibleId: String?,
    @SerializedName("bookId")
    val bookId: String?,
    @SerializedName("content")
    val content: List<ContentX>?,
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("number")
    val number: String?,
    @SerializedName("orgId")
    val orgId: String?,
    @SerializedName("reference")
    val reference: String?,
    @SerializedName("verseCount")
    val verseCount: Int?
)