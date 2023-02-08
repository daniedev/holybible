package github.daniedev.holybible.model.bibleverse


import com.google.gson.annotations.SerializedName

data class Previous(
    @SerializedName("id")
    val id: String?,
    @SerializedName("number")
    val number: String?
)