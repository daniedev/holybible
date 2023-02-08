package github.daniedev.holybible.model.chapterv2


import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("attrs")
    val attrs: Attrs?,
    @SerializedName("items")
    val items: List<Item>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?
)