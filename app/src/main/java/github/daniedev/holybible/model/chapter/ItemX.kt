package github.daniedev.holybible.model.chapter


import com.google.gson.annotations.SerializedName

data class ItemX(
    @SerializedName("attrs")
    val attrs: AttrsXX?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("type")
    val type: String?
)