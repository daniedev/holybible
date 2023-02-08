package github.daniedev.holybible.model.bibleverse


import com.google.gson.annotations.SerializedName

data class Next(
    @SerializedName("id")
    val id: String?,
    @SerializedName("number")
    val number: String?
)