package github.daniedev.holybible.model.chapterv2


import com.google.gson.annotations.SerializedName

data class ContentX(
    @SerializedName("attrs")
    val attrs: AttrsXXX?,
    @SerializedName("items")
    val items: List<ItemXX>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?
)