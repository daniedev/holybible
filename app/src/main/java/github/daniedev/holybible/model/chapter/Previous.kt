package github.daniedev.holybible.model.chapter


import com.google.gson.annotations.SerializedName

data class Previous(
    @SerializedName("bookId")
    val bookId: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("number")
    val number: String?
)