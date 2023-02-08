package github.daniedev.holybible.model.bibleverse


import com.google.gson.annotations.SerializedName

data class AttrsX(
    @SerializedName("number")
    val number: String?,
    @SerializedName("sid")
    val sid: String?,
    @SerializedName("style")
    val style: String?,
    @SerializedName("verseId")
    val verseId: String?,
    @SerializedName("verseOrgIds")
    val verseOrgIds: List<String>?
)