package github.daniedev.holybible.model.chapterv2


import com.google.gson.annotations.SerializedName

data class ItemXX(
    @SerializedName("attrs")
    val attrs: AttrsXXXX?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("type")
    val type: String?
)