package github.daniedev.holybible.model.chapter


import com.google.gson.annotations.SerializedName

data class Chapter(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("meta")
    val meta: Meta?
)