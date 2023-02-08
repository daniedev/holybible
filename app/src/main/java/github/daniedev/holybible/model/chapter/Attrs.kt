package github.daniedev.holybible.model.chapter


import com.google.gson.annotations.SerializedName

data class Attrs(
    @SerializedName("style")
    val style: String?
)