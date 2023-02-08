package github.daniedev.holybible.model.chapterv2


import com.google.gson.annotations.SerializedName

data class AttrsX(
    @SerializedName("style")
    val style: String?,
    @SerializedName("verseId")
    val verseId: String?,
    @SerializedName("verseOrgIds")
    val verseOrgIds: List<String>?
)