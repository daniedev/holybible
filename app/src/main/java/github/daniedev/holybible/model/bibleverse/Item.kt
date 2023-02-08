package github.daniedev.holybible.model.bibleverse

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("attrs")
    val attrs: AttrsX?,
    @SerializedName("items")
    val items: List<ItemX>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("text")
    val text: String,
    @SerializedName("type")
    val type: String?
)