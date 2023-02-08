package github.daniedev.holybible.model.bibleverse


import com.google.gson.annotations.SerializedName

data class Attrs(
    @SerializedName("style")
    val style: String?
)