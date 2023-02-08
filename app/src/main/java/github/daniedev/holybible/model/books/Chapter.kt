package github.daniedev.holybible.model.books


import com.google.gson.annotations.SerializedName

data class Chapter(
    @SerializedName("bibleId")
    val bibleId: String?,
    @SerializedName("bookId")
    val bookId: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("number")
    val number: String?,
    @SerializedName("position")
    val position: Int?
)