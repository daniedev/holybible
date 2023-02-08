package github.daniedev.holybible.model.chapterv2


import com.google.gson.annotations.SerializedName

data class Previous(
    @SerializedName("bookId")
    val bookId: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("number")
    val number: String?
)