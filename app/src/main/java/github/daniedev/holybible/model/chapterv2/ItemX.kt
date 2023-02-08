package github.daniedev.holybible.model.chapterv2


import com.google.gson.annotations.SerializedName

data class ItemX(
    @SerializedName("attrs")
    val attrs: AttrsXX?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("type")
    val type: String?
)