package github.daniedev.holybible.model.bibleverse


import com.google.gson.annotations.SerializedName

data class ItemX(
    @SerializedName("text")
    val text: String?,
    @SerializedName("type")
    val type: String?
)